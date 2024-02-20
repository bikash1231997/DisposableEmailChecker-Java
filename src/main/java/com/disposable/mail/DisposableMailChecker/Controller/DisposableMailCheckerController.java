/*
 * Bikash Mahapatra
 *
 * Copyright (C) 2024.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */
package com.disposable.mail.DisposableMailChecker.Controller;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

@RestController
@RequestMapping("/mail")
public class DisposableMailCheckerController {

	public String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

	@Autowired
	private ResourceLoader resourceLoader;

	@RequestMapping(value = "/check-disposible-domain", method = RequestMethod.GET)
	public ResponseEntity<Boolean> checkDomain(@RequestParam("domain") String domain)
			throws IOException, CsvValidationException {
		Resource resource = resourceLoader.getResource("classpath:" + "DisposibleMails.csv");
		List<String> csvData = new ArrayList<>();
		try (CSVReader csvReader = new CSVReader(new InputStreamReader(resource.getInputStream()))) {
			String[] line;
			boolean skipLicense = true;
			while ((line = csvReader.readNext()) != null) {
				if (skipLicense) {
					if (Arrays.toString(line).equals("[-----------------------------------------------------------------------]")) {
						skipLicense = false;
						continue;
					} else {
						continue;
					}
				}
				String row = String.join(",", line);
				csvData.add(row);
			}
		}
		if (csvData.contains(domain)) {
			return new ResponseEntity<>(true, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(false, HttpStatus.OK);
		}
	}

	@RequestMapping(value = "/check-disposible-email", method = RequestMethod.GET)
	public ResponseEntity<?> checkEmail(@PathVariable(name = "email") String email)
			throws IOException, CsvValidationException {
		if (email.matches(emailRegex)) {
			return checkDomain(email.split("@")[1]);
		} else {
			return ResponseEntity.badRequest().body("Invalid email address");
		}
	}
}
