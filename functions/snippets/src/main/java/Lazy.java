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

// [START functions_tips_lazy_globals]
// [START run_tips_global_lazy]

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Lazy {
  // Always initialized (at cold-start)
  // Warning: Class variables used in Servlet classes must be thread-safe,
  // or else might introduce race conditions in your code.
  private static final int nonLazyGlobal = fileWideComputation();
  // Declared at cold-start, but only initialized if/when the function executes
  private static Integer lazyGlobal = null;

  public void lazyGlobal(HttpServletRequest request, HttpServletResponse response)
      throws IOException {
    // This value is initialized only if (and when) the function is called
    if (lazyGlobal == null) {
      lazyGlobal = functionSpecificComputation();
    }

    PrintWriter writer = response.getWriter();
    writer.write(String.format("Lazy global: %s; non-lazy global: %s", lazyGlobal, nonLazyGlobal));
  }

  private static int functionSpecificComputation() {
    int[] numbers = new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9};
    return Arrays.stream(numbers).sum();
  }

  private static int fileWideComputation() {
    int[] numbers = new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9};
    return Arrays.stream(numbers).reduce((t, x) -> t * x).getAsInt();
  }
}
// [END run_tips_global_lazy]
// [END functions_tips_lazy_globals]
