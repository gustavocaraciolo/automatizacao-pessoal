package com.br.gustavocaraciolo.service;

import com.br.gustavocaraciolo.domain.ReservaQuadraTenis;
import com.br.gustavocaraciolo.repository.ReservaQuadraTenisRepository;
import com.br.gustavocaraciolo.utils.DiaDaSemanaEnum;
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

    @Scheduled(cron = "0 0 4 * * *", zone = "Africa/Luanda")
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
                buildEmail(reservaQuadraTenis, DiaDaSemanaEnum.SEGUNDA_FEIRA);
            }
            if (compararDataHora(dtNowZonedDateTime, dtTercaFeira) && !reservaQuadraTenis.getTercaFeiraEmailEnviado()) {
                buildEmail(reservaQuadraTenis, DiaDaSemanaEnum.TERCA_FEIRA);
            }
            if (compararDataHora(dtNowZonedDateTime, dtQuartaFeira) && !reservaQuadraTenis.getQuintaFeiraEmailEnviado()) {
                buildEmail(reservaQuadraTenis, DiaDaSemanaEnum.QUARTA_FEIRA);
            }
            if (compararDataHora(dtNowZonedDateTime, dtQuintaFeira) && !reservaQuadraTenis.getQuintaFeiraEmailEnviado()) {
                buildEmail(reservaQuadraTenis, DiaDaSemanaEnum.QUINTA_FEIRA);
            }
            if (compararDataHora(dtNowZonedDateTime, dtSextaFeira) && !reservaQuadraTenis.getSextaFeiraEmailEnviado()) {
                buildEmail(reservaQuadraTenis, DiaDaSemanaEnum.SEXTA_FEIRA);
            }
            if (compararDataHora(dtNowZonedDateTime, dtSabado) && !reservaQuadraTenis.getSabadoEmailEnviado()) {
                buildEmail(reservaQuadraTenis, DiaDaSemanaEnum.SABADO);
            }
            if (compararDataHora(dtNowZonedDateTime, dtDomingo) && !reservaQuadraTenis.getDomingoEmailEnviado()) {
                buildEmail(reservaQuadraTenis, DiaDaSemanaEnum.DOMINGO);
            }
        }
    }

    private void buildEmail(ReservaQuadraTenis reservaQuadraTenis, DiaDaSemanaEnum dia) {
        String templateEmailOriginal = reservaQuadraTenis.getTemplateEmail();
        /*switch (dia) {
            case SEGUNDA_FEIRA:
                reservaQuadraTenis.setCorpoEmail(templateEmailOriginal.replace("{data_hora}",
                    dia.label + " às " + reservaQuadraTenis.getSegundafeira().getHour() + ":"
                        + reservaQuadraTenis.getSegundafeira().getMinute()));
                this.mailService.sendReservaQuadraTenisMail(reservaQuadraTenis);
                reservaQuadraTenis.setSegundaFeiraEmailEnviado(true);
                reservaQuadraTenisRepository.save(reservaQuadraTenis);
                break;
            case TERCA_FEIRA:
                reservaQuadraTenis.setCorpoEmail(templateEmailOriginal.replace("{data_hora}",
                    dia.label + " às " + reservaQuadraTenis.getTercafeira().getHour() + ":"
                        + reservaQuadraTenis.getTercafeira().getMinute()));
                this.mailService.sendReservaQuadraTenisMail(reservaQuadraTenis);
                reservaQuadraTenis.setTercaFeiraEmailEnviado(true);
                reservaQuadraTenisRepository.save(reservaQuadraTenis);
                break;
            case QUARTA_FEIRA:
                reservaQuadraTenis.setCorpoEmail(templateEmailOriginal.replace("{data_hora}",
                    dia.label + " às " + reservaQuadraTenis.getQuartafeira().getHour() + ":"
                        + reservaQuadraTenis.getQuartafeira().getMinute()));
                this.mailService.sendReservaQuadraTenisMail(reservaQuadraTenis);
                reservaQuadraTenis.setQuartaFeiraEmailEnviado(true);
                reservaQuadraTenisRepository.save(reservaQuadraTenis);
                break;
            case QUINTA_FEIRA:
                reservaQuadraTenis.setCorpoEmail(templateEmailOriginal.replace("{data_hora}",
                    dia.label + " às " + reservaQuadraTenis.getQuintafeira().getHour() + ":"
                        + reservaQuadraTenis.getQuintafeira().getMinute()));
                this.mailService.sendReservaQuadraTenisMail(reservaQuadraTenis);
                reservaQuadraTenis.setQuintaFeiraEmailEnviado(true);
                reservaQuadraTenisRepository.save(reservaQuadraTenis);
                break;
            case SEXTA_FEIRA:
                reservaQuadraTenis.setCorpoEmail(templateEmailOriginal.replace("{data_hora}",
                    dia.label + " às " + reservaQuadraTenis.getSextafeira().getHour() + ":"
                        + reservaQuadraTenis.getSextafeira().getMinute()));
                this.mailService.sendReservaQuadraTenisMail(reservaQuadraTenis);
                reservaQuadraTenis.setSextaFeiraEmailEnviado(true);
                reservaQuadraTenisRepository.save(reservaQuadraTenis);
                break;
            case SABADO:
                reservaQuadraTenis.setCorpoEmail(templateEmailOriginal.replace("{data_hora}",
                    dia.label + " às " + reservaQuadraTenis.getSabado().getHour() + ":"
                        + reservaQuadraTenis.getSabado().getMinute()));
                this.mailService.sendReservaQuadraTenisMail(reservaQuadraTenis);
                reservaQuadraTenis.setSabadoEmailEnviado(true);
                reservaQuadraTenisRepository.save(reservaQuadraTenis);
                break;
            case DOMINGO:
                reservaQuadraTenis.setCorpoEmail(templateEmailOriginal.replace("{data_hora}",
                    dia.label + " às " + reservaQuadraTenis.getDomingo().getHour() + ":"
                        + reservaQuadraTenis.getDomingo().getMinute()));
                this.mailService.sendReservaQuadraTenisMail(reservaQuadraTenis);
                reservaQuadraTenis.setDomingoEmailEnviado(true);
                reservaQuadraTenisRepository.save(reservaQuadraTenis);
                break;
        }*/
    }

    private boolean compararDataHora(ZonedDateTime dtNowZonedDateTime, ZonedDateTime dataHora) {
        boolean retorno = false;
        if (dataHora.getYear() == dtNowZonedDateTime.getYear()) {
            if (dataHora.getMonth() == dtNowZonedDateTime.getMonth()) {
                if (dataHora.getDayOfMonth() == dtNowZonedDateTime.getDayOfMonth()) {
                    retorno = true;
                }
            }
        }
        return retorno;
    }
}
