import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { of } from 'rxjs';

import { AtividadeService } from '../service/atividade.service';

import { AtividadeComponent } from './atividade.component';

describe('Atividade Management Component', () => {
  let comp: AtividadeComponent;
  let fixture: ComponentFixture<AtividadeComponent>;
  let service: AtividadeService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      declarations: [AtividadeComponent],
    })
      .overrideTemplate(AtividadeComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(AtividadeComponent);
    comp = fixture.componentInstance;
    service = TestBed.inject(AtividadeService);

    const headers = new HttpHeaders();
    jest.spyOn(service, 'query').mockReturnValue(
      of(
        new HttpResponse({
          body: [{ id: 123 }],
          headers,
        })
      )
    );
  });

  it('Should call load all on init', () => {
    // WHEN
    comp.ngOnInit();

    // THEN
    expect(service.query).toHaveBeenCalled();
    expect(comp.atividades?.[0]).toEqual(expect.objectContaining({ id: 123 }));
  });
});
