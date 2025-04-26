package com.example.ipcalculator.controller;

import com.example.ipcalculator.service.IpCalculatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Controller responsável por gerenciar as requisições da calculadora de IP
 */
@Controller
public class IpCalculatorController {

    @Autowired
    private IpCalculatorService ipCalculatorService;    

    /**
     * Renderiza a página inicial da calculadora
     */
    @GetMapping("/")
    public String index() {
        return "index";
    }

    /**
     * Processa o cálculo do IP e retorna os resultados em formato JSON
     * @param ipAddress Endereço IP informado pelo usuário
     * @param subnetMask Máscara de sub-rede informada pelo usuário
     * @return Resposta JSON com os resultados do cálculo ou mensagem de erro
     */
    @PostMapping("/calculate")
    @ResponseBody
    public ResponseEntity<?> calculate(@RequestParam String ipAddress,
                                     @RequestParam String subnetMask) {
        try {
            // Calcula todas as informações necessárias
            String networkAddress = ipCalculatorService.calculateNetworkAddress(ipAddress, subnetMask);
            String broadcastAddress = ipCalculatorService.calculateBroadcastAddress(ipAddress, subnetMask);
            int numberOfHosts = ipCalculatorService.calculateNumberOfHosts(subnetMask);
            int cidr = ipCalculatorService.calculateCIDR(subnetMask);
            String networkClass = ipCalculatorService.getNetworkClass(ipAddress);

            // Prepara a resposta com os resultados
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("networkAddress", networkAddress);
            response.put("broadcastAddress", broadcastAddress);
            response.put("numberOfHosts", numberOfHosts);
            response.put("cidr", cidr);
            response.put("networkClass", networkClass);
            
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            // Em caso de erro, retorna mensagem apropriada
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("success", false);
            errorResponse.put("error", "Formato de endereço IP ou máscara de sub-rede inválido");
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }
} 