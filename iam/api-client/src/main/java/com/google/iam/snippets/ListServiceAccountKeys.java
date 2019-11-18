/* Copyright 2019 Google LLC
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

package com.google.iam.snippets;

// [START iam_list_keys]
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.iam.v1.Iam;
import com.google.api.services.iam.v1.IamScopes;
import com.google.api.services.iam.v1.model.ServiceAccountKey;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.List;

public class ListServiceAccountKeys {

  // Lists all keys for a service account.
  public static void listKeys(String projectId) {
    // String projectId = "my-project-id";
    
    Iam service = null;
    try {
      service = initService();
    } catch (IOException | GeneralSecurityException e) {
      System.out.println("Unable to initialize service: \n" + e.toString());
      return;
    }

    try {
      List<ServiceAccountKey> keys =
          service
              .projects()
              .serviceAccounts()
              .keys()
              .list(
                  "projects/-/serviceAccounts/"
                      + "your-service-account-name@"
                      + projectId
                      + ".iam.gserviceaccount.com")
              .execute()
              .getKeys();

      for (ServiceAccountKey key : keys) {
        System.out.println("Key: " + key.getName());
      }
    } catch (IOException e) {
      System.out.println("Unable to list service account keys: \n" + e.toString());
    }
  }

  private static Iam initService() throws GeneralSecurityException, IOException {
    // Use the Application Default Credentials strategy for authentication. For more info, see:
    // https://cloud.google.com/docs/authentication/production#finding_credentials_automatically
    GoogleCredential credential =
        GoogleCredential.getApplicationDefault()
            .createScoped(Collections.singleton(IamScopes.CLOUD_PLATFORM));
    // Initialize the IAM service, which can be used to send requests to the IAM API.
    Iam service =
        new Iam.Builder(
                GoogleNetHttpTransport.newTrustedTransport(),
                JacksonFactory.getDefaultInstance(),
                credential)
            .setApplicationName("service-account-keys")
            .build();
    return service;
  }
}
// [END iam_list_keys]
