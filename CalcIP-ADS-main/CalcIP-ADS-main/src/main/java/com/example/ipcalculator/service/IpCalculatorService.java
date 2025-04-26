package com.example.ipcalculator.service;

import org.springframework.stereotype.Service;
import java.util.Arrays;

@Service
public class IpCalculatorService {
    
    /**
     * Calcula o endereço de rede usando operação AND bit a bit entre IP e máscara
     * @param ipAddress Endereço IP (ex: 192.168.1.1)
     * @param subnetMask Máscara de sub-rede (ex: 255.255.255.0)
     * @return Endereço de rede calculado
     */
    public String calculateNetworkAddress(String ipAddress, String subnetMask) {
        String[] ipOctets = ipAddress.split("\\.");
        String[] maskOctets = subnetMask.split("\\.");
        
        int[] networkAddress = new int[4];
        for (int i = 0; i < 4; i++) {
            networkAddress[i] = Integer.parseInt(ipOctets[i]) & Integer.parseInt(maskOctets[i]);
        }
        
        return Arrays.stream(networkAddress)
                .mapToObj(String::valueOf)
                .reduce((a, b) -> a + "." + b)
                .orElse("");
    }
    
    /**
     * Calcula o endereço de broadcast usando operação OR bit a bit entre IP e máscara invertida
     * @param ipAddress Endereço IP
     * @param subnetMask Máscara de sub-rede
     * @return Endereço de broadcast calculado
     */
    public String calculateBroadcastAddress(String ipAddress, String subnetMask) {
        String[] ipOctets = ipAddress.split("\\.");
        String[] maskOctets = subnetMask.split("\\.");
        
        int[] broadcastAddress = new int[4];
        for (int i = 0; i < 4; i++) {
            int mask = Integer.parseInt(maskOctets[i]);
            broadcastAddress[i] = Integer.parseInt(ipOctets[i]) | (~mask & 0xFF);
        }
        
        return Arrays.stream(broadcastAddress)
                .mapToObj(String::valueOf)
                .reduce((a, b) -> a + "." + b)
                .orElse("");
    }
    
    /**
     * Calcula o número de hosts disponíveis na rede
     * @param subnetMask Máscara de sub-rede
     * @return Número de hosts disponíveis (2^n - 2, onde n é o número de bits de host)
     */
    public int calculateNumberOfHosts(String subnetMask) {
        String[] maskOctets = subnetMask.split("\\.");
        int totalBits = 32;
        int networkBits = 0;
        
        for (String octet : maskOctets) {
            int value = Integer.parseInt(octet);
            for (int i = 7; i >= 0; i--) {
                if ((value & (1 << i)) != 0) {
                    networkBits++;
                }
            }
        }
        
        return (int) Math.pow(2, totalBits - networkBits) - 2;
    }

    /**
     * Calcula a notação CIDR da máscara de sub-rede
     * @param subnetMask Máscara de sub-rede
     * @return Número de bits da máscara (ex: 24 para 255.255.255.0)
     */
    public int calculateCIDR(String subnetMask) {
        String[] maskOctets = subnetMask.split("\\.");
        int cidr = 0;
        
        for (String octet : maskOctets) {
            int value = Integer.parseInt(octet);
            for (int i = 7; i >= 0; i--) {
                if ((value & (1 << i)) != 0) {
                    cidr++;
                }
            }
        }
        
        return cidr;
    }

    /**
     * Determina a classe da rede baseado no primeiro octeto do IP
     * @param ipAddress Endereço IP
     * @return Classe da rede (A, B, C, D ou E)
     */
    public String getNetworkClass(String ipAddress) {
        String[] ipOctets = ipAddress.split("\\.");
        int firstOctet = Integer.parseInt(ipOctets[0]);
        
        if (firstOctet >= 1 && firstOctet <= 126) {
            return "Class A";
        } else if (firstOctet >= 128 && firstOctet <= 191) {
            return "Class B";
        } else if (firstOctet >= 192 && firstOctet <= 223) {
            return "Class C";
        } else if (firstOctet >= 224 && firstOctet <= 239) {
            return "Class D (Multicast)";
        } else if (firstOctet >= 240 && firstOctet <= 255) {
            return "Class E (Experimental)";
        } else {
            return "Unknown";
        }
    }
} 