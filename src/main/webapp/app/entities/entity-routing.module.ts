import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'reserva-quadra-tenis',
        data: { pageTitle: 'automatizacaoPessoalApp.reservaQuadraTenis.home.title' },
        loadChildren: () => import('./reserva-quadra-tenis/reserva-quadra-tenis.module').then(m => m.ReservaQuadraTenisModule),
      },
      {
        path: 'blocos',
        data: { pageTitle: 'automatizacaoPessoalApp.blocos.home.title' },
        loadChildren: () => import('./blocos/blocos.module').then(m => m.BlocosModule),
      },
      {
        path: 'cronograma-diario',
        data: { pageTitle: 'automatizacaoPessoalApp.cronogramaDiario.home.title' },
        loadChildren: () => import('./cronograma-diario/cronograma-diario.module').then(m => m.CronogramaDiarioModule),
      },
      {
        path: 'atividade',
        data: { pageTitle: 'automatizacaoPessoalApp.atividade.home.title' },
        loadChildren: () => import('./atividade/atividade.module').then(m => m.AtividadeModule),
      },
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ]),
  ],
})
export class EntityRoutingModule {}
