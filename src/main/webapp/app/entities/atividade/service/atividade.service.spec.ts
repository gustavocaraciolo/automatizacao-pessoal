import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { IAtividade, Atividade } from '../atividade.model';

import { AtividadeService } from './atividade.service';

describe('Atividade Service', () => {
  let service: AtividadeService;
  let httpMock: HttpTestingController;
  let elemDefault: IAtividade;
  let expectedResult: IAtividade | IAtividade[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(AtividadeService);
    httpMock = TestBed.inject(HttpTestingController);

    elemDefault = {
      id: 0,
      cor: 'AAAAAAA',
      descricao: 'AAAAAAA',
    };
  });

  describe('Service methods', () => {
    it('should find an element', () => {
      const returnedFromService = Object.assign({}, elemDefault);

      service.find(123).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(elemDefault);
    });

    it('should create a Atividade', () => {
      const returnedFromService = Object.assign(
        {
          id: 0,
        },
        elemDefault
      );

      const expected = Object.assign({}, returnedFromService);

      service.create(new Atividade()).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a Atividade', () => {
      const returnedFromService = Object.assign(
        {
          id: 1,
          cor: 'BBBBBB',
          descricao: 'BBBBBB',
        },
        elemDefault
      );

      const expected = Object.assign({}, returnedFromService);

      service.update(expected).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a Atividade', () => {
      const patchObject = Object.assign(
        {
          descricao: 'BBBBBB',
        },
        new Atividade()
      );

      const returnedFromService = Object.assign(patchObject, elemDefault);

      const expected = Object.assign({}, returnedFromService);

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of Atividade', () => {
      const returnedFromService = Object.assign(
        {
          id: 1,
          cor: 'BBBBBB',
          descricao: 'BBBBBB',
        },
        elemDefault
      );

      const expected = Object.assign({}, returnedFromService);

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toContainEqual(expected);
    });

    it('should delete a Atividade', () => {
      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult);
    });

    describe('addAtividadeToCollectionIfMissing', () => {
      it('should add a Atividade to an empty array', () => {
        const atividade: IAtividade = { id: 123 };
        expectedResult = service.addAtividadeToCollectionIfMissing([], atividade);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(atividade);
      });

      it('should not add a Atividade to an array that contains it', () => {
        const atividade: IAtividade = { id: 123 };
        const atividadeCollection: IAtividade[] = [
          {
            ...atividade,
          },
          { id: 456 },
        ];
        expectedResult = service.addAtividadeToCollectionIfMissing(atividadeCollection, atividade);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a Atividade to an array that doesn't contain it", () => {
        const atividade: IAtividade = { id: 123 };
        const atividadeCollection: IAtividade[] = [{ id: 456 }];
        expectedResult = service.addAtividadeToCollectionIfMissing(atividadeCollection, atividade);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(atividade);
      });

      it('should add only unique Atividade to an array', () => {
        const atividadeArray: IAtividade[] = [{ id: 123 }, { id: 456 }, { id: 68561 }];
        const atividadeCollection: IAtividade[] = [{ id: 123 }];
        expectedResult = service.addAtividadeToCollectionIfMissing(atividadeCollection, ...atividadeArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const atividade: IAtividade = { id: 123 };
        const atividade2: IAtividade = { id: 456 };
        expectedResult = service.addAtividadeToCollectionIfMissing([], atividade, atividade2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(atividade);
        expect(expectedResult).toContain(atividade2);
      });

      it('should accept null and undefined values', () => {
        const atividade: IAtividade = { id: 123 };
        expectedResult = service.addAtividadeToCollectionIfMissing([], null, atividade, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(atividade);
      });

      it('should return initial array if no Atividade is added', () => {
        const atividadeCollection: IAtividade[] = [{ id: 123 }];
        expectedResult = service.addAtividadeToCollectionIfMissing(atividadeCollection, undefined, null);
        expect(expectedResult).toEqual(atividadeCollection);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
