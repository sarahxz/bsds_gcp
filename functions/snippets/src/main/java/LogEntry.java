/*
 * Copyright 2019 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

// [START functions_log_stackdriver]
import java.io.IOException;
import java.util.Map;
import java.util.logging.Logger;

public class LogEntry {

  private static Logger LOGGER = Logger.getLogger(LogEntry.class.getName());

  public String helloPubSub(PubSubMessage message) throws IOException {
    if (message.data.isEmpty()) {
      message.data = "World";
    }
    String res = String.format("Hello, %s", message.data);
    LOGGER.info(res);
    return res;
  }

  // A Pub/Sub message.
  // Make sure to include the PubSubMessage class verbatim
  // The GCF environment will marshal the request data into it.
  public class PubSubMessage {
    String data;
    Map<String, String> attributes;
    String messageId;
    String publishTime;
  }
}

// [END functions_log_stackdriver]