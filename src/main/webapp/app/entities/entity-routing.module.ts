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
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ]),
  ],
})
export class EntityRoutingModule {}
