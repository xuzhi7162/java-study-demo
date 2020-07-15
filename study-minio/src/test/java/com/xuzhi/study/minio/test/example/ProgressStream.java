package com.xuzhi.study.minio.test.example;/*
 * MinIO Java SDK for Amazon S3 Compatible Cloud Storage, (C) 2017 MinIO, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import me.tongfei.progressbar.ProgressBar;
import me.tongfei.progressbar.ProgressBarStyle;

import java.io.IOException;
import java.io.InputStream;

public class ProgressStream extends InputStream {
  private InputStream in;
  private ProgressBar pb;

  /**
   * ProgressStream implements an extends InputStream while also writing out the read progress on
   * console. ProgressStream can be used as a direct replacement for any InputStream compatible
   * input.
   *
   * @param msg Custom message string.
   * @param style Custom progress bar style.
   * @param stream InputStream to be wrapped.
   * @throws IOException For any exception generated by the InputStream.
   */
  public ProgressStream(String msg, ProgressBarStyle style, InputStream stream) throws IOException {
    this(msg, style, (long) stream.available(), stream);
  }

  /**
   * ProgressStream implements an extends InputStream while also writing out the read progress on
   * console. ProgressStream can be used as a direct replacement for any InputStream compatible
   * input.
   *
   * @param msg Custom message string.
   * @param style Custom progress bar style.
   * @param size Size of the progress bar.
   * @param stream InputStream to be wrapped.
   * @throws IOException For any exception generated by the InputStream.
   */
  public ProgressStream(String msg, ProgressBarStyle style, long size, InputStream stream)
      throws IOException {
    super();

    // Allocate the reader.
    this.in = stream;

    // Initialize progress bar.
    this.pb = new ProgressBar(msg, size, style);
    this.pb.start();
  }

  @Override
  public int available() throws IOException {
    return this.in.available();
  }

  @Override
  public void close() throws IOException {
    this.pb.stop();
    this.in.close();
    return;
  }

  @Override
  public int read() throws IOException {
    this.pb.step();
    return this.in.read();
  }

  @Override
  public int read(byte[] toStore) throws IOException {
    int readBytes = this.in.read(toStore);
    this.pb.stepBy(readBytes); // Update progress bar.
    return readBytes;
  }

  @Override
  public int read(byte[] toStore, int off, int len) throws IOException {
    int readBytes = this.in.read(toStore, off, len);
    this.pb.stepBy(readBytes);
    return readBytes;
  }

  @Override
  public long skip(long n) throws IOException {
    this.pb.stepTo(n);
    return this.in.skip(n);
  }

  @Override
  public boolean markSupported() {
    return false;
  }
}
