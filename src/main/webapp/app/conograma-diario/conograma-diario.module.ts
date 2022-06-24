import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { SharedModule } from 'app/shared/shared.module';

import { CONOGRAMA_DIARIO_ROUTE } from './conograma-diario.route';
import { ConogramaDiarioComponent } from './conograma-diario.component';

@NgModule({
  imports: [SharedModule, RouterModule.forRoot([CONOGRAMA_DIARIO_ROUTE], { useHash: true })],
  declarations: [ConogramaDiarioComponent],
  entryComponents: [],
  providers: [],
  schemas: [CUSTOM_ELEMENTS_SCHEMA],
})
export class AutomatizacaoPessoalAppConogramaDiarioModule {}
