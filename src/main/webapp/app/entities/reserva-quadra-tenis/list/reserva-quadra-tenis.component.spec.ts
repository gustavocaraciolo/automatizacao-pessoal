import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of } from 'rxjs';

import { ReservaQuadraTenisService } from '../service/reserva-quadra-tenis.service';

import { ReservaQuadraTenisComponent } from './reserva-quadra-tenis.component';

describe('ReservaQuadraTenis Management Component', () => {
  let comp: ReservaQuadraTenisComponent;
  let fixture: ComponentFixture<ReservaQuadraTenisComponent>;
  let service: ReservaQuadraTenisService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [
        RouterTestingModule.withRoutes([{ path: 'reserva-quadra-tenis', component: ReservaQuadraTenisComponent }]),
        HttpClientTestingModule,
      ],
      declarations: [ReservaQuadraTenisComponent],
      providers: [
        {
          provide: ActivatedRoute,
          useValue: {
            data: of({
              defaultSort: 'id,asc',
            }),
            queryParamMap: of(
              jest.requireActual('@angular/router').convertToParamMap({
                page: '1',
                size: '1',
                sort: 'id,desc',
              })
            ),
          },
        },
      ],
    })
      .overrideTemplate(ReservaQuadraTenisComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(ReservaQuadraTenisComponent);
    comp = fixture.componentInstance;
    service = TestBed.inject(ReservaQuadraTenisService);

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
    expect(comp.reservaQuadraTenis?.[0]).toEqual(expect.objectContaining({ id: 123 }));
  });

  it('should load a page', () => {
    // WHEN
    comp.loadPage(1);

    // THEN
    expect(service.query).toHaveBeenCalled();
    expect(comp.reservaQuadraTenis?.[0]).toEqual(expect.objectContaining({ id: 123 }));
  });

  it('should calculate the sort attribute for an id', () => {
    // WHEN
    comp.ngOnInit();

    // THEN
    expect(service.query).toHaveBeenCalledWith(expect.objectContaining({ sort: ['id,desc'] }));
  });

  it('should calculate the sort attribute for a non-id attribute', () => {
    // INIT
    comp.ngOnInit();

    // GIVEN
    comp.predicate = 'name';

    // WHEN
    comp.loadPage(1);

    // THEN
    expect(service.query).toHaveBeenLastCalledWith(expect.objectContaining({ sort: ['name,desc', 'id'] }));
  });
});
