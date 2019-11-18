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

// [START functions_concepts_filesystem]
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FileSystem {

  // Lists the files in the current directory.
  public void listFiles(HttpServletRequest request, HttpServletResponse response)
      throws IOException {
    File curDir = new File(".");
    File[] files = curDir.listFiles();
    PrintWriter writer = response.getWriter();
    writer.write("Files: \n");
    for (File f : files) {
      writer.write(String.format("\t%s\n", f.getName()));
    }
  }
}

// [END functions_concepts_filesystem]
