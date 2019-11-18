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

// [START functions_helloworld_background]
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HelloBackground {

  // Use GSON (https://github.com/google/gson) to parse JSON content.
  private Gson gsonParser = new Gson();

  // Background Cloud Function.
  public void helloBackground(HttpServletRequest request, HttpServletResponse response)
      throws IOException {
    String contentType = request.getContentType();
    String name = "World";
    JsonObject body = gsonParser.fromJson(request.getReader(), JsonObject.class);
    if (body.has("name")) {
      name = body.get("name").getAsString();
    }
    PrintWriter writer = response.getWriter();
    writer.write(String.format("Hello %s!", name));
  }
}

// [END functions_helloworld_background]
