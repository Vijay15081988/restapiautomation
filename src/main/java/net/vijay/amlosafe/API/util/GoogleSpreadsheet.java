package net.vijay.amlosafe.API.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.security.GeneralSecurityException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.SheetsScopes;
import com.google.api.services.sheets.v4.model.ValueRange;
import com.google.auth.http.HttpCredentialsAdapter;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.common.collect.Lists;



public class GoogleSpreadsheet {

	HttpRequestInitializer requestInitializer;
	JsonFactory JSON_FACTORY;
	NetHttpTransport HTTP_TRANSPORT;
	Sheets service;

	// final String spreadsheetId = "1epgwqfe9I0bawbh426Pu4rDkvSfIpoR50NkSes4PAUo";
	private static final String CREDENTIALS_FILE_PATH = "/src/main/resources/UserManagementAPIData/ServiceCredentials.json";
	private static String cwd =System.getProperty("user.dir");
	public Object[][] spreadSheetData = null;
	public static Logger log = LogManager.getLogger(GoogleSpreadsheet.class.getName());

	public Object[][] readDataFromGoogleSpreadsheet(String spreadsheetId,String rangeValDBAuth) throws IOException, GeneralSecurityException {
		log.info("Reading Data From Google Spreadsheet started");
		GoogleCredentials credentials = GoogleCredentials.fromStream(new FileInputStream(cwd + CREDENTIALS_FILE_PATH))
				.createScoped(Lists.newArrayList(SheetsScopes.SPREADSHEETS));
		requestInitializer = new HttpCredentialsAdapter(credentials);
		// Build a new authorized API client service.
		JSON_FACTORY = JacksonFactory.getDefaultInstance();
		HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
		service = new Sheets.Builder(HTTP_TRANSPORT, JSON_FACTORY, requestInitializer)
				.setApplicationName("TestAutomation").build();
		final String range =rangeValDBAuth;

		ValueRange response = service.spreadsheets().values().get(spreadsheetId, range).execute();

		List<List<Object>> values = response.getValues();
		int rowSize = values.size();
		int colSize = values.get(0).size();
		spreadSheetData = new Object[rowSize][colSize];
		int i = 0;

		if (values == null || values.isEmpty()) {
			System.out.println("No data found.");
		} else {

			for (List row : values) {

				for (int j = 0; j < colSize; j++) {
					spreadSheetData[i][j] = row.get(j);
					
				}
				i++;
			}
			
		}
	 log.info("Reading Data From Google Spreadsheet Ended");
     return spreadSheetData;
	}

}
