package co.analisys.biblioteca.controller;

import co.analisys.biblioteca.messaging.PagoCreadoEvent;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.UUID;

import static co.analisys.biblioteca.messaging.RabbitConfig.*;

@RestController
@RequestMapping("/pagos")
public class PagoController {

    private final RabbitTemplate rabbitTemplate;

    public PagoController(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @PostMapping
    public String crearPago(@RequestParam BigDecimal monto, @RequestParam(required = false) String usuarioId) {
        String pagoId = UUID.randomUUID().toString();
        PagoCreadoEvent event = new PagoCreadoEvent(pagoId, monto, usuarioId);
        rabbitTemplate.convertAndSend(EXCHANGE, RK_PAGO_CREADO, event);
        return pagoId;
    }
}
