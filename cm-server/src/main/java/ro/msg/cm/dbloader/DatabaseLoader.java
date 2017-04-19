/*
 * Copyright 2015 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package ro.msg.cm.dbloader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import ro.msg.cm.model.Candidate;
import ro.msg.cm.repository.CandidateRepository;

@Component
public class DatabaseLoader implements CommandLineRunner {

	private final CandidateRepository repository;

	@Autowired
	public DatabaseLoader(CandidateRepository repository) {
		this.repository = repository;
	}

	@Override
	public void run(String... strings) throws Exception {
		// clean previous data in the table
		this.repository.deleteAll();

		this.repository.save(new Candidate("Miralem", "Pjanic"));
		this.repository.save(new Candidate("Sami", "Khedira"));
		this.repository.save(new Candidate("Mario", "Mandzukic"));
		this.repository.save(new Candidate("Paulo", "Dybala"));
		this.repository.save(new Candidate("Alex", "Sandro"));
	}
}