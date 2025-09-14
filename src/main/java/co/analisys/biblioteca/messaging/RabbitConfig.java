package co.analisys.biblioteca.messaging;

import org.springframework.amqp.core.*;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    public static final String EXCHANGE = "gimnasio.exchange";

    // Routing keys we care about
    public static final String RK_INSCRIPCION = "clase.inscripcion.creada";
    public static final String RK_HORARIO = "clase.horario.actualizado";
    public static final String RK_PAGO_CREADO = "pago.creado";

    // Queues
    public static final String Q_NOTIFICACIONES = "notificaciones.queue"; // fanout style via same routing key pattern
    public static final String Q_ANALYTICS = "analytics.queue";
    public static final String Q_HORARIOS = "horarios.queue";
    public static final String Q_PAGOS = "pagos.queue";
    public static final String Q_PAGOS_DLQ = "pagos.dlq";

    @Bean
    public TopicExchange gimnasioExchange() {
        return new TopicExchange(EXCHANGE, true, false);
    }

    // Inscripciones (dos colas escuchando el mismo routing key => pub/sub)
    @Bean
    public Queue notificacionesQueue() { return QueueBuilder.durable(Q_NOTIFICACIONES).build(); }
    @Bean
    public Queue analyticsQueue() { return QueueBuilder.durable(Q_ANALYTICS).build(); }

    // Horarios
    @Bean
    public Queue horariosQueue() { return QueueBuilder.durable(Q_HORARIOS).build(); }

    // Pagos + DLQ (TTL simulada opcional; aquí solo declaramos DLQ)
    @Bean
    public Queue pagosQueue() {
        return QueueBuilder.durable(Q_PAGOS)
                .withArgument("x-dead-letter-exchange", "") // default exchange
                .withArgument("x-dead-letter-routing-key", Q_PAGOS_DLQ)
                .build();
    }

    @Bean
    public Queue pagosDlq() { return QueueBuilder.durable(Q_PAGOS_DLQ).build(); }

    // Bindings
    @Bean
    public Binding bindingNotificaciones(Queue notificacionesQueue, TopicExchange gimnasioExchange) {
        return BindingBuilder.bind(notificacionesQueue).to(gimnasioExchange).with(RK_INSCRIPCION);
    }
    @Bean
    public Binding bindingAnalytics(Queue analyticsQueue, TopicExchange gimnasioExchange) {
        return BindingBuilder.bind(analyticsQueue).to(gimnasioExchange).with(RK_INSCRIPCION);
    }
    @Bean
    public Binding bindingHorarios(Queue horariosQueue, TopicExchange gimnasioExchange) {
        return BindingBuilder.bind(horariosQueue).to(gimnasioExchange).with(RK_HORARIO);
    }
    @Bean
    public Binding bindingPagos(Queue pagosQueue, TopicExchange gimnasioExchange) {
        return BindingBuilder.bind(pagosQueue).to(gimnasioExchange).with(RK_PAGO_CREADO);
    }

    @Bean
    public MessageConverter jsonMessageConverter() { return new Jackson2JsonMessageConverter(); }
}
