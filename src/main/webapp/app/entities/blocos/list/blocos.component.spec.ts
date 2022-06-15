import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { of } from 'rxjs';

import { BlocosService } from '../service/blocos.service';

import { BlocosComponent } from './blocos.component';

describe('Blocos Management Component', () => {
  let comp: BlocosComponent;
  let fixture: ComponentFixture<BlocosComponent>;
  let service: BlocosService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      declarations: [BlocosComponent],
    })
      .overrideTemplate(BlocosComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(BlocosComponent);
    comp = fixture.componentInstance;
    service = TestBed.inject(BlocosService);

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
    expect(comp.blocos?.[0]).toEqual(expect.objectContaining({ id: 123 }));
  });
});
