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
            ZonedDateTime dtQuartaFeira = reservaQuadraTenis.getTercafeira().minusDays(2);
            ZonedDateTime dtQuintaFeira = reservaQuadraTenis.getTercafeira().minusDays(2);
            ZonedDateTime dtSextaFeira = reservaQuadraTenis.getTercafeira().minusDays(2);
            ZonedDateTime dtSabado = reservaQuadraTenis.getTercafeira().minusDays(2);
            ZonedDateTime dtDomingo = reservaQuadraTenis.getTercafeira().minusDays(2);
            if(dtSegundaFeira.isEqual(dtNowZonedDateTime)) {
                this.mailService.sendEmail("gustavocaraciolo@gmail.com","Email de teste","Email de teste content Segunda-Feira",false, true);
            }if(dtTercaFeira.isEqual(dtNowZonedDateTime)) {
                this.mailService.sendEmail("gustavocaraciolo@gmail.com","Email de teste","Email de teste content Terça-Feira",false, true);
            }if(dtQuartaFeira.isEqual(dtNowZonedDateTime)) {
                this.mailService.sendEmail("gustavocaraciolo@gmail.com","Email de teste","Email de teste content Quarta-Feira",false, true);
            }if(dtQuintaFeira.isEqual(dtNowZonedDateTime)) {
                this.mailService.sendEmail("gustavocaraciolo@gmail.com","Email de teste","Email de teste content Quinta-Feira",false, true);
            }if(dtSextaFeira.isEqual(dtNowZonedDateTime)) {
                this.mailService.sendEmail("gustavocaraciolo@gmail.com","Email de teste","Email de teste content Sexta-Feira",false, true);
            }if(dtSabado.isEqual(dtNowZonedDateTime)) {
                this.mailService.sendEmail("gustavocaraciolo@gmail.com","Email de teste","Email de teste content Sábado",false, true);
            }if(dtDomingo.isEqual(dtNowZonedDateTime)) {
                this.mailService.sendEmail("gustavocaraciolo@gmail.com","Email de teste","Email de teste content Domingo",false, true);
            }
        }
    }
}
