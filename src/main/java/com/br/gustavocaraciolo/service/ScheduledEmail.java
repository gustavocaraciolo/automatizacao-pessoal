package com.br.gustavocaraciolo.service;

import com.br.gustavocaraciolo.domain.ReservaQuadraTenis;
import com.br.gustavocaraciolo.repository.ReservaQuadraTenisRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

@Service
public class ScheduledEmail {

    private final ReservaQuadraTenisRepository reservaQuadraTenisRepository;
    private final MailService mailService;

    public ScheduledEmail(ReservaQuadraTenisRepository reservaQuadraTenisRepository, MailService mailService) {
        this.reservaQuadraTenisRepository = reservaQuadraTenisRepository;
        this.mailService = mailService;
    }

    @Scheduled(fixedRate = 100000)
    public void scheduleFixedRateTask() {
        ZonedDateTime dtNowZonedDateTime = ZonedDateTime.now(ZoneId.of("Africa/Luanda"));
        System.out.println(" ---- " + dtNowZonedDateTime);
        List<ReservaQuadraTenis> all = this.reservaQuadraTenisRepository.findAll();

        for (ReservaQuadraTenis reservaQuadraTenis : all) {
            ZonedDateTime dtSegundaFeira = reservaQuadraTenis.getSegundafeira().minusDays(2);
            ZonedDateTime dtTercaFeira = reservaQuadraTenis.getTercafeira().minusDays(2);
            ZonedDateTime dtQuartaFeira = reservaQuadraTenis.getQuartafeira().minusDays(2);
            ZonedDateTime dtQuintaFeira = reservaQuadraTenis.getQuintafeira().minusDays(2);
            ZonedDateTime dtSextaFeira = reservaQuadraTenis.getSegundafeira().minusDays(2);
            ZonedDateTime dtSabado = reservaQuadraTenis.getSabado().minusDays(2);
            ZonedDateTime dtDomingo = reservaQuadraTenis.getDomingo().minusDays(2);

            if (compararDataHora(dtNowZonedDateTime, dtSegundaFeira)) {
                sendEmail(reservaQuadraTenis, "segunda-feira");
            }
            if (compararDataHora(dtNowZonedDateTime, dtTercaFeira)) {
                sendEmail(reservaQuadraTenis, "terça-feira");
            }
            if (compararDataHora(dtNowZonedDateTime, dtQuartaFeira)) {
                sendEmail(reservaQuadraTenis, "quarta-feira");
            }
            if (compararDataHora(dtNowZonedDateTime, dtQuintaFeira)) {
                sendEmail(reservaQuadraTenis, "quinta-feira");
            }
            if (compararDataHora(dtNowZonedDateTime, dtSextaFeira)) {
                sendEmail(reservaQuadraTenis, "sexta-feira");
            }
            if (compararDataHora(dtNowZonedDateTime, dtSabado)) {
                sendEmail(reservaQuadraTenis, "sábado");
            }
            if (compararDataHora(dtNowZonedDateTime, dtDomingo)) {
                sendEmail(reservaQuadraTenis, "domingo");
            }
        }
    }

    private boolean compararDataHora(ZonedDateTime dtNowZonedDateTime, ZonedDateTime dataHora) {
        boolean retorno = false;
        if (dataHora.getYear() == dtNowZonedDateTime.getYear()) {
            if (dataHora.getMonth() == dtNowZonedDateTime.getMonth()) {
                if (dataHora.getDayOfMonth() == dtNowZonedDateTime.getDayOfMonth()) {
                    if (dataHora.getHour() == dtNowZonedDateTime.getHour()) {
                        if (dataHora.getMinute() == dtNowZonedDateTime.getMinute()) {
                            retorno = true;
                        }
                    }
                }
            }
        }
        return retorno;
    }

    private void sendEmail(ReservaQuadraTenis reservaQuadraTenis, String dia) {
        String templateEmail = reservaQuadraTenis.getTemplateEmail().replace("{data_hora}", dia + " às " + reservaQuadraTenis.getSegundafeira().getHour());
        this.mailService.sendEmail(reservaQuadraTenis.getEmailDestino(), "Reserva Quadra de Tênis", templateEmail, false, true);
    }
}
