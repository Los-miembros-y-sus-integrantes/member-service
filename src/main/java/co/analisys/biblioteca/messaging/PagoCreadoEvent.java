package co.analisys.biblioteca.messaging;

import java.io.Serializable;
import java.math.BigDecimal;

public class PagoCreadoEvent implements Serializable {
    private String pagoId;
    private BigDecimal monto;
    private String usuarioId;

    public PagoCreadoEvent() {}

    public PagoCreadoEvent(String pagoId, BigDecimal monto, String usuarioId) {
        this.pagoId = pagoId;
        this.monto = monto;
        this.usuarioId = usuarioId;
    }

    public String getPagoId() { return pagoId; }
    public void setPagoId(String pagoId) { this.pagoId = pagoId; }
    public BigDecimal getMonto() { return monto; }
    public void setMonto(BigDecimal monto) { this.monto = monto; }
    public String getUsuarioId() { return usuarioId; }
    public void setUsuarioId(String usuarioId) { this.usuarioId = usuarioId; }
}
