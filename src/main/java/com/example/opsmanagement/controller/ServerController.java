package com.example.opsmanagement.controller;

import com.example.opsmanagement.entity.Server;
import com.example.opsmanagement.repository.ServerRepository;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/servers")
public class ServerController {

    private final ServerRepository serverRepository;

    public ServerController(ServerRepository serverRepository) {
        this.serverRepository = serverRepository;
    }

    @GetMapping
    public List<Server> getAllServers() {
        return serverRepository.findAll();
    }

    @PostMapping
    public Server createServer(@RequestBody Server server) {
        if (server.getStatus() == null || server.getStatus().isBlank()) {
            server.setStatus("정상");
        }

        return serverRepository.save(server);
    }

    @PutMapping("/{id}")
    public Server updateServer(@PathVariable Long id, @RequestBody Server updatedServer) {
        Server server = serverRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Server not found"));

        server.setName(updatedServer.getName());
        server.setIp(updatedServer.getIp());
        server.setDescription(updatedServer.getDescription());

        if (updatedServer.getStatus() != null && !updatedServer.getStatus().isBlank()) {
            server.setStatus(updatedServer.getStatus());
        }

        return serverRepository.save(server);
    }

    @DeleteMapping("/{id}")
    public String deleteServer(@PathVariable Long id) {
        serverRepository.deleteById(id);
        return "Server deleted";
    }

    @GetMapping("/summary")
    public Map<String, Long> getServerSummary() {
        List<Server> servers = serverRepository.findAll();

        long normalCount = servers.stream()
                .filter(s -> "정상".equals(s.getStatus()))
                .count();

        long warningCount = servers.stream()
                .filter(s -> "점검".equals(s.getStatus()))
                .count();

        long dangerCount = servers.stream()
                .filter(s -> "위험".equals(s.getStatus()))
                .count();

        Map<String, Long> result = new HashMap<>();
        result.put("normal", normalCount);
        result.put("warning", warningCount);
        result.put("danger", dangerCount);

        return result;
    }
}