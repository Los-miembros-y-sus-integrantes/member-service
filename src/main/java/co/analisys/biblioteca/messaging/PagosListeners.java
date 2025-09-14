package co.analisys.biblioteca.messaging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;

import java.util.Random;

import static co.analisys.biblioteca.messaging.RabbitConfig.*;

@Component
public class PagosListeners {

    private static final Logger log = LoggerFactory.getLogger(PagosListeners.class);
    private final Random random = new Random();

    @RabbitListener(queues = Q_PAGOS)
    public void onPagoCreado(PagoCreadoEvent event) {
        log.info("[Pago] Recibido pago id={} monto={} usuario={}", event.getPagoId(), event.getMonto(), event.getUsuarioId());
        // Simulamos fallo aleatorio 40% para enviar a DLQ
        if (random.nextDouble() < 0.4) {
            log.warn("[Pago] Fallo procesando pago {}. Se enviará a DLQ", event.getPagoId());
            throw new AmqpRejectAndDontRequeueException("Fallo simulado procesando pago");
        }
        log.info("[Pago] Procesado correctamente pago {}", event.getPagoId());
    }

    @RabbitListener(queues = Q_PAGOS_DLQ)
    public void onPagoDeadLetter(PagoCreadoEvent event) {
        log.error("[DLQ] Pago en DLQ id={} monto={} usuario={}", event.getPagoId(), event.getMonto(), event.getUsuarioId());
    }
}
