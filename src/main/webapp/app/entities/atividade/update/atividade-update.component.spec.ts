import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { AtividadeService } from '../service/atividade.service';
import { IAtividade, Atividade } from '../atividade.model';

import { AtividadeUpdateComponent } from './atividade-update.component';

describe('Atividade Management Update Component', () => {
  let comp: AtividadeUpdateComponent;
  let fixture: ComponentFixture<AtividadeUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let atividadeService: AtividadeService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([])],
      declarations: [AtividadeUpdateComponent],
      providers: [
        FormBuilder,
        {
          provide: ActivatedRoute,
          useValue: {
            params: from([{}]),
          },
        },
      ],
    })
      .overrideTemplate(AtividadeUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(AtividadeUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    atividadeService = TestBed.inject(AtividadeService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should call Atividade query and add missing value', () => {
      const atividade: IAtividade = { id: 456 };
      const blocos: IAtividade = { id: 47409 };
      atividade.blocos = blocos;

      const atividadeCollection: IAtividade[] = [{ id: 58049 }];
      jest.spyOn(atividadeService, 'query').mockReturnValue(of(new HttpResponse({ body: atividadeCollection })));
      const additionalAtividades = [blocos];
      const expectedCollection: IAtividade[] = [...additionalAtividades, ...atividadeCollection];
      jest.spyOn(atividadeService, 'addAtividadeToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({ atividade });
      comp.ngOnInit();

      expect(atividadeService.query).toHaveBeenCalled();
      expect(atividadeService.addAtividadeToCollectionIfMissing).toHaveBeenCalledWith(atividadeCollection, ...additionalAtividades);
      expect(comp.atividadesSharedCollection).toEqual(expectedCollection);
    });

    it('Should update editForm', () => {
      const atividade: IAtividade = { id: 456 };
      const blocos: IAtividade = { id: 80338 };
      atividade.blocos = blocos;

      activatedRoute.data = of({ atividade });
      comp.ngOnInit();

      expect(comp.editForm.value).toEqual(expect.objectContaining(atividade));
      expect(comp.atividadesSharedCollection).toContain(blocos);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<Atividade>>();
      const atividade = { id: 123 };
      jest.spyOn(atividadeService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ atividade });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: atividade }));
      saveSubject.complete();

      // THEN
      expect(comp.previousState).toHaveBeenCalled();
      expect(atividadeService.update).toHaveBeenCalledWith(atividade);
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<Atividade>>();
      const atividade = new Atividade();
      jest.spyOn(atividadeService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ atividade });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: atividade }));
      saveSubject.complete();

      // THEN
      expect(atividadeService.create).toHaveBeenCalledWith(atividade);
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<Atividade>>();
      const atividade = { id: 123 };
      jest.spyOn(atividadeService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ atividade });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(atividadeService.update).toHaveBeenCalledWith(atividade);
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });

  describe('Tracking relationships identifiers', () => {
    describe('trackAtividadeById', () => {
      it('Should return tracked Atividade primary key', () => {
        const entity = { id: 123 };
        const trackResult = comp.trackAtividadeById(0, entity);
        expect(trackResult).toEqual(entity.id);
      });
    });
  });
});
