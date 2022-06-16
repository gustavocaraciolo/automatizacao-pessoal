import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { BlocosDetailComponent } from './blocos-detail.component';

describe('Blocos Management Detail Component', () => {
  let comp: BlocosDetailComponent;
  let fixture: ComponentFixture<BlocosDetailComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [BlocosDetailComponent],
      providers: [
        {
          provide: ActivatedRoute,
          useValue: { data: of({ blocos: { id: 123 } }) },
        },
      ],
    })
      .overrideTemplate(BlocosDetailComponent, '')
      .compileComponents();
    fixture = TestBed.createComponent(BlocosDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load blocos on init', () => {
      // WHEN
      comp.ngOnInit();

      // THEN
      expect(comp.blocos).toEqual(expect.objectContaining({ id: 123 }));
    });
  });
});
