import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import dayjs from 'dayjs/esm';

import { DATE_FORMAT } from 'app/config/input.constants';
import { ICronogramaDiario, CronogramaDiario } from '../cronograma-diario.model';

import { CronogramaDiarioService } from './cronograma-diario.service';

describe('CronogramaDiario Service', () => {
  let service: CronogramaDiarioService;
  let httpMock: HttpTestingController;
  let elemDefault: ICronogramaDiario;
  let expectedResult: ICronogramaDiario | ICronogramaDiario[] | boolean | null;
  let currentDate: dayjs.Dayjs;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(CronogramaDiarioService);
    httpMock = TestBed.inject(HttpTestingController);
    currentDate = dayjs();

    elemDefault = {
      id: 0,
      dia: currentDate,
    };
  });

  describe('Service methods', () => {
    it('should find an element', () => {
      const returnedFromService = Object.assign(
        {
          dia: currentDate.format(DATE_FORMAT),
        },
        elemDefault
      );

      service.find(123).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(elemDefault);
    });

    it('should create a CronogramaDiario', () => {
      const returnedFromService = Object.assign(
        {
          id: 0,
          dia: currentDate.format(DATE_FORMAT),
        },
        elemDefault
      );

      const expected = Object.assign(
        {
          dia: currentDate,
        },
        returnedFromService
      );

      service.create(new CronogramaDiario()).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a CronogramaDiario', () => {
      const returnedFromService = Object.assign(
        {
          id: 1,
          dia: currentDate.format(DATE_FORMAT),
        },
        elemDefault
      );

      const expected = Object.assign(
        {
          dia: currentDate,
        },
        returnedFromService
      );

      service.update(expected).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a CronogramaDiario', () => {
      const patchObject = Object.assign({}, new CronogramaDiario());

      const returnedFromService = Object.assign(patchObject, elemDefault);

      const expected = Object.assign(
        {
          dia: currentDate,
        },
        returnedFromService
      );

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of CronogramaDiario', () => {
      const returnedFromService = Object.assign(
        {
          id: 1,
          dia: currentDate.format(DATE_FORMAT),
        },
        elemDefault
      );

      const expected = Object.assign(
        {
          dia: currentDate,
        },
        returnedFromService
      );

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toContainEqual(expected);
    });

    it('should delete a CronogramaDiario', () => {
      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult);
    });

    describe('addCronogramaDiarioToCollectionIfMissing', () => {
      it('should add a CronogramaDiario to an empty array', () => {
        const cronogramaDiario: ICronogramaDiario = { id: 123 };
        expectedResult = service.addCronogramaDiarioToCollectionIfMissing([], cronogramaDiario);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(cronogramaDiario);
      });

      it('should not add a CronogramaDiario to an array that contains it', () => {
        const cronogramaDiario: ICronogramaDiario = { id: 123 };
        const cronogramaDiarioCollection: ICronogramaDiario[] = [
          {
            ...cronogramaDiario,
          },
          { id: 456 },
        ];
        expectedResult = service.addCronogramaDiarioToCollectionIfMissing(cronogramaDiarioCollection, cronogramaDiario);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a CronogramaDiario to an array that doesn't contain it", () => {
        const cronogramaDiario: ICronogramaDiario = { id: 123 };
        const cronogramaDiarioCollection: ICronogramaDiario[] = [{ id: 456 }];
        expectedResult = service.addCronogramaDiarioToCollectionIfMissing(cronogramaDiarioCollection, cronogramaDiario);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(cronogramaDiario);
      });

      it('should add only unique CronogramaDiario to an array', () => {
        const cronogramaDiarioArray: ICronogramaDiario[] = [{ id: 123 }, { id: 456 }, { id: 78935 }];
        const cronogramaDiarioCollection: ICronogramaDiario[] = [{ id: 123 }];
        expectedResult = service.addCronogramaDiarioToCollectionIfMissing(cronogramaDiarioCollection, ...cronogramaDiarioArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const cronogramaDiario: ICronogramaDiario = { id: 123 };
        const cronogramaDiario2: ICronogramaDiario = { id: 456 };
        expectedResult = service.addCronogramaDiarioToCollectionIfMissing([], cronogramaDiario, cronogramaDiario2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(cronogramaDiario);
        expect(expectedResult).toContain(cronogramaDiario2);
      });

      it('should accept null and undefined values', () => {
        const cronogramaDiario: ICronogramaDiario = { id: 123 };
        expectedResult = service.addCronogramaDiarioToCollectionIfMissing([], null, cronogramaDiario, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(cronogramaDiario);
      });

      it('should return initial array if no CronogramaDiario is added', () => {
        const cronogramaDiarioCollection: ICronogramaDiario[] = [{ id: 123 }];
        expectedResult = service.addCronogramaDiarioToCollectionIfMissing(cronogramaDiarioCollection, undefined, null);
        expect(expectedResult).toEqual(cronogramaDiarioCollection);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
