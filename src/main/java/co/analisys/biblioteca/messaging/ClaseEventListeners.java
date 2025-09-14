package co.analisys.biblioteca.messaging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import static co.analisys.biblioteca.messaging.RabbitConfig.*;

@Component
public class ClaseEventListeners {

    private static final Logger log = LoggerFactory.getLogger(ClaseEventListeners.class);

    // Reutilizamos estructuras de evento usando Map fallback (Jackson convierte a LinkedHashMap) si no tenemos clases específicas aquí.
    @RabbitListener(queues = Q_NOTIFICACIONES)
    public void onInscripcionNotificaciones(Object payload) {
        log.info("[Notificaciones] Nueva inscripción recibida {}", payload);
    }

    @RabbitListener(queues = Q_ANALYTICS)
    public void onInscripcionAnalytics(Object payload) {
        log.info("[Analytics] Evento inscripción para análisis {}", payload);
    }

    @RabbitListener(queues = Q_HORARIOS)
    public void onHorarioActualizado(Object payload) {
        log.info("[Horarios] Cambio de horario recibido {}", payload);
    }
}
