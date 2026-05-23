package com.example.opsmanagement.controller;

import com.example.opsmanagement.entity.Server;
import com.example.opsmanagement.repository.ServerRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
public class DashboardController {

    private final ServerRepository serverRepository;
    private final Random random = new Random();

    public DashboardController(ServerRepository serverRepository) {
        this.serverRepository = serverRepository;
    }

    @GetMapping({"/", "/dashboard"})
    public String dashboard(Model model) {

        List<Server> savedServers = serverRepository.findAll();
        List<Map<String, Object>> servers = new ArrayList<>();

        for (Server server : savedServers) {
            servers.add(buildServerData(server));
        }

        long totalServers = servers.size();

        long normalServers = servers.stream()
                .filter(s -> s.get("status").equals("정상"))
                .count();

        long warningServers = servers.stream()
                .filter(s -> s.get("status").equals("점검"))
                .count();

        long dangerServers = servers.stream()
                .filter(s -> s.get("status").equals("위험"))
                .count();

        model.addAttribute("servers", servers);
        model.addAttribute("totalServers", totalServers);
        model.addAttribute("normalServers", normalServers);
        model.addAttribute("warningServers", warningServers);
        model.addAttribute("dangerServers", dangerServers);

        return "dashboard";
    }

    @GetMapping("/server/new")
    public String serverForm() {
        return "server-form";
    }

    @PostMapping("/server/new")
    public String createServer(@RequestParam String name,
                               @RequestParam String ip,
                               @RequestParam String description) {

        Server server = new Server(name, ip, description);
        serverRepository.save(server);

        return "redirect:/dashboard";
    }

    @GetMapping("/server/delete/{id}")
    public String deleteServer(@PathVariable Long id) {
        serverRepository.deleteById(id);
        return "redirect:/dashboard";
    }

    private Map<String, Object> buildServerData(Server server) {

        int cpuUsage = random.nextInt(100);
        int memoryUsage = random.nextInt(100);
        int diskUsage = random.nextInt(100);

        String status = determineStatus(cpuUsage, memoryUsage, diskUsage);

        Map<String, Object> map = new HashMap<>();
        map.put("id", server.getId());
        map.put("name", server.getName());
        map.put("ip", server.getIp());
        map.put("description", server.getDescription());
        map.put("cpuUsage", cpuUsage);
        map.put("memoryUsage", memoryUsage);
        map.put("diskUsage", diskUsage);
        map.put("status", status);

        return map;
    }

    private String determineStatus(int cpu, int memory, int disk) {
        if (cpu >= 80 || memory >= 80 || disk >= 80) {
            return "위험";
        }
        if (cpu >= 60 || memory >= 60 || disk >= 60) {
            return "점검";
        }
        return "정상";
    }
}