package dev.antoniogrillo.esempiocinecaavanzato.dto.response;

import java.time.LocalDateTime;

public record MessageDTO(String message, LocalDateTime timestamp,int errorCode) {
}
