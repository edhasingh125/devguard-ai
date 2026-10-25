package com.devguard.api;

import com.devguard.api.repository.RepositoryRepository;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class RepositoryApiTests {
    @Autowired MockMvc mvc;
    @Autowired RepositoryRepository repositories;

    @Test
    void registersReadsAndRejectsDuplicateRepository() throws Exception {
        String name = "test-" + UUID.randomUUID();
        String url = "https://github.com/devguard-tests/" + name;
        mvc.perform(post("/api/repositories").contentType("application/json")
                        .content("{\"url\":\"" + url + ".git/\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.url").value(url))
                .andExpect(jsonPath("$.name").value(name))
                .andExpect(jsonPath("$.owner").value("devguard-tests"))
                .andExpect(jsonPath("$.status").value("REGISTERED"))
                .andExpect(jsonPath("$.createdAt").exists())
                .andExpect(header().exists("Location"));

        var repository = repositories.findByUrl(url).orElseThrow();
        assertThat(repository.getDefaultBranch()).isNull();
        assertThat(repository.getLanguage()).isNull();
        mvc.perform(get("/api/repositories/" + repository.getId()))
                .andExpect(status().isOk()).andExpect(jsonPath("$.url").value(url));
        mvc.perform(get("/api/repositories"))
                .andExpect(status().isOk()).andExpect(jsonPath("$").isArray());
        mvc.perform(post("/api/repositories").contentType("application/json")
                        .content("{\"url\":\"" + url.toUpperCase() + "\"}"))
                .andExpect(status().isConflict()).andExpect(jsonPath("$.status").value(409));
    }

    @Test
    void returnsJsonForInvalidInputAndMissingResources() throws Exception {
        for (String body : new String[] {
                "{}", "{\"url\":\"\"}", "{\"url\":\"https://example.com/owner/repo\"}",
                "{\"url\":\"https://github.com/owner/repo/tree/main\"}",
                "{\"url\":\"https://github.com/owner/..\"}", "{"
        }) {
            mvc.perform(post("/api/repositories").contentType("application/json").content(body))
                    .andExpect(status().isBadRequest()).andExpect(jsonPath("$.status").value(400));
        }
        mvc.perform(get("/api/repositories/-1"))
                .andExpect(status().isNotFound()).andExpect(jsonPath("$.status").value(404));
        mvc.perform(get("/api/repositories/not-a-number"))
                .andExpect(status().isBadRequest()).andExpect(jsonPath("$.status").value(400));
    }

    @Test
    void healthEndpointsArePublic() throws Exception {
        mvc.perform(get("/api/health")).andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("UP"))
                .andExpect(jsonPath("$.service").value("devguard-api"))
                .andExpect(jsonPath("$.version").value("0.1.0"));
        mvc.perform(get("/actuator/health")).andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("UP"));
    }
}
