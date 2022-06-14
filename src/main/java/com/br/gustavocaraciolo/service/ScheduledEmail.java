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

    @Scheduled(fixedRate = 1000)
    public void scheduleFixedRateTask() {
        ZonedDateTime dtNowZonedDateTime = ZonedDateTime.now(ZoneId.of("Africa/Luanda"));
        List<ReservaQuadraTenis> all = this.reservaQuadraTenisRepository.findAll();

        for (ReservaQuadraTenis reservaQuadraTenis : all) {
            ZonedDateTime dtSegundaFeira = reservaQuadraTenis.getSegundafeira().minusDays(2);
            ZonedDateTime dtTercaFeira = reservaQuadraTenis.getTercafeira().minusDays(2);
            ZonedDateTime dtQuartaFeira = reservaQuadraTenis.getQuartafeira().minusDays(2);
            ZonedDateTime dtQuintaFeira = reservaQuadraTenis.getQuintafeira().minusDays(2);
            ZonedDateTime dtSextaFeira = reservaQuadraTenis.getSegundafeira().minusDays(2);
            ZonedDateTime dtSabado = reservaQuadraTenis.getSabado().minusDays(2);
            ZonedDateTime dtDomingo = reservaQuadraTenis.getDomingo().minusDays(2);

            if (compararDataHora(dtNowZonedDateTime, dtSegundaFeira) && !reservaQuadraTenis.getSegundaFeiraEmailEnviado()) {
                reservaQuadraTenis.setTemplateEmail(reservaQuadraTenis.getTemplateEmail().replace("{data_hora}",
                    "segunda-feira às " + reservaQuadraTenis.getSegundafeira().getHour() + ":" + reservaQuadraTenis.getSegundafeira().getMinute()));
                this.mailService.sendReservaQuadraTenisMail(reservaQuadraTenis);
                reservaQuadraTenis.setSegundaFeiraEmailEnviado(true);
                reservaQuadraTenisRepository.save(reservaQuadraTenis);
            }
            if (compararDataHora(dtNowZonedDateTime, dtTercaFeira) && !reservaQuadraTenis.getTercaFeiraEmailEnviado()) {
                reservaQuadraTenis.setTemplateEmail(reservaQuadraTenis.getTemplateEmail().replace("{data_hora}",
                    "terça-feira às " + reservaQuadraTenis.getTercafeira().getHour() + ":" + reservaQuadraTenis.getTercafeira().getMinute()));
                this.mailService.sendReservaQuadraTenisMail(reservaQuadraTenis);
                reservaQuadraTenis.setTercaFeiraEmailEnviado(true);
                reservaQuadraTenisRepository.save(reservaQuadraTenis);
            }
            if (compararDataHora(dtNowZonedDateTime, dtQuartaFeira) && !reservaQuadraTenis.getQuintaFeiraEmailEnviado()) {
                reservaQuadraTenis.setTemplateEmail(reservaQuadraTenis.getTemplateEmail().replace("{data_hora}",
                    "quarta-feira às " + reservaQuadraTenis.getQuartafeira().getHour() + ":" + reservaQuadraTenis.getQuartafeira().getMinute()));
                this.mailService.sendReservaQuadraTenisMail(reservaQuadraTenis);
                reservaQuadraTenis.setQuartaFeiraEmailEnviado(true);
                reservaQuadraTenisRepository.save(reservaQuadraTenis);
            }
            if (compararDataHora(dtNowZonedDateTime, dtQuintaFeira) && !reservaQuadraTenis.getQuintaFeiraEmailEnviado()) {
                reservaQuadraTenis.setTemplateEmail(reservaQuadraTenis.getTemplateEmail().replace("{data_hora}",
                    "quinta-feira às " + reservaQuadraTenis.getQuintafeira().getHour() + ":" + reservaQuadraTenis.getQuintafeira().getMinute()));
                this.mailService.sendReservaQuadraTenisMail(reservaQuadraTenis);
                reservaQuadraTenis.setQuintaFeiraEmailEnviado(true);
                reservaQuadraTenisRepository.save(reservaQuadraTenis);
            }
            if (compararDataHora(dtNowZonedDateTime, dtSextaFeira) && !reservaQuadraTenis.getSextaFeiraEmailEnviado()) {
                reservaQuadraTenis.setTemplateEmail(reservaQuadraTenis.getTemplateEmail().replace("{data_hora}",
                    "sexta-feira às " + reservaQuadraTenis.getSextafeira().getHour() + ":" + reservaQuadraTenis.getSextafeira().getMinute()));
                this.mailService.sendReservaQuadraTenisMail(reservaQuadraTenis);
                reservaQuadraTenis.setSextaFeiraEmailEnviado(true);
                reservaQuadraTenisRepository.save(reservaQuadraTenis);
            }
            if (compararDataHora(dtNowZonedDateTime, dtSabado) && !reservaQuadraTenis.getSabadoEmailEnviado()) {
                reservaQuadraTenis.setTemplateEmail(reservaQuadraTenis.getTemplateEmail().replace("{data_hora}",
                    "sábado às " + reservaQuadraTenis.getSabado().getHour() + ":" + reservaQuadraTenis.getSabado().getMinute()));
                this.mailService.sendReservaQuadraTenisMail(reservaQuadraTenis);
                reservaQuadraTenis.setSabadoEmailEnviado(true);
                reservaQuadraTenisRepository.save(reservaQuadraTenis);
            }
            if (compararDataHora(dtNowZonedDateTime, dtDomingo) && !reservaQuadraTenis.getDomingoEmailEnviado()) {
                reservaQuadraTenis.setTemplateEmail(reservaQuadraTenis.getTemplateEmail().replace("{data_hora}",
                    "domingo às " + reservaQuadraTenis.getDomingo().getHour() + ":" + reservaQuadraTenis.getDomingo().getMinute()));
                this.mailService.sendReservaQuadraTenisMail(reservaQuadraTenis);
                reservaQuadraTenis.setDomingoEmailEnviado(true);
                reservaQuadraTenisRepository.save(reservaQuadraTenis);
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
}
