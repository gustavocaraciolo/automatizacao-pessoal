import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import dayjs from 'dayjs/esm';

import { DATE_FORMAT, DATE_TIME_FORMAT } from 'app/config/input.constants';
import { IReservaQuadraTenis, ReservaQuadraTenis } from '../reserva-quadra-tenis.model';

import { ReservaQuadraTenisService } from './reserva-quadra-tenis.service';

describe('ReservaQuadraTenis Service', () => {
  let service: ReservaQuadraTenisService;
  let httpMock: HttpTestingController;
  let elemDefault: IReservaQuadraTenis;
  let expectedResult: IReservaQuadraTenis | IReservaQuadraTenis[] | boolean | null;
  let currentDate: dayjs.Dayjs;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(ReservaQuadraTenisService);
    httpMock = TestBed.inject(HttpTestingController);
    currentDate = dayjs();

    elemDefault = {
      id: 0,
      emailOrigem: 'AAAAAAA',
      emailDestino: 'AAAAAAA',
      templateEmail: 'AAAAAAA',
      semana: currentDate,
      segundafeira: currentDate,
      tercafeira: currentDate,
      quartafeira: currentDate,
      quintafeira: currentDate,
      sextafeira: currentDate,
      sabado: currentDate,
      domingo: currentDate,
    };
  });

  describe('Service methods', () => {
    it('should find an element', () => {
      const returnedFromService = Object.assign(
        {
          semana: currentDate.format(DATE_FORMAT),
          segundafeira: currentDate.format(DATE_TIME_FORMAT),
          tercafeira: currentDate.format(DATE_TIME_FORMAT),
          quartafeira: currentDate.format(DATE_TIME_FORMAT),
          quintafeira: currentDate.format(DATE_TIME_FORMAT),
          sextafeira: currentDate.format(DATE_TIME_FORMAT),
          sabado: currentDate.format(DATE_TIME_FORMAT),
          domingo: currentDate.format(DATE_TIME_FORMAT),
        },
        elemDefault
      );

      service.find(123).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(elemDefault);
    });

    it('should create a ReservaQuadraTenis', () => {
      const returnedFromService = Object.assign(
        {
          id: 0,
          semana: currentDate.format(DATE_FORMAT),
          segundafeira: currentDate.format(DATE_TIME_FORMAT),
          tercafeira: currentDate.format(DATE_TIME_FORMAT),
          quartafeira: currentDate.format(DATE_TIME_FORMAT),
          quintafeira: currentDate.format(DATE_TIME_FORMAT),
          sextafeira: currentDate.format(DATE_TIME_FORMAT),
          sabado: currentDate.format(DATE_TIME_FORMAT),
          domingo: currentDate.format(DATE_TIME_FORMAT),
        },
        elemDefault
      );

      const expected = Object.assign(
        {
          semana: currentDate,
          segundafeira: currentDate,
          tercafeira: currentDate,
          quartafeira: currentDate,
          quintafeira: currentDate,
          sextafeira: currentDate,
          sabado: currentDate,
          domingo: currentDate,
        },
        returnedFromService
      );

      service.create(new ReservaQuadraTenis()).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a ReservaQuadraTenis', () => {
      const returnedFromService = Object.assign(
        {
          id: 1,
          emailOrigem: 'BBBBBB',
          emailDestino: 'BBBBBB',
          templateEmail: 'BBBBBB',
          semana: currentDate.format(DATE_FORMAT),
          segundafeira: currentDate.format(DATE_TIME_FORMAT),
          tercafeira: currentDate.format(DATE_TIME_FORMAT),
          quartafeira: currentDate.format(DATE_TIME_FORMAT),
          quintafeira: currentDate.format(DATE_TIME_FORMAT),
          sextafeira: currentDate.format(DATE_TIME_FORMAT),
          sabado: currentDate.format(DATE_TIME_FORMAT),
          domingo: currentDate.format(DATE_TIME_FORMAT),
        },
        elemDefault
      );

      const expected = Object.assign(
        {
          semana: currentDate,
          segundafeira: currentDate,
          tercafeira: currentDate,
          quartafeira: currentDate,
          quintafeira: currentDate,
          sextafeira: currentDate,
          sabado: currentDate,
          domingo: currentDate,
        },
        returnedFromService
      );

      service.update(expected).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a ReservaQuadraTenis', () => {
      const patchObject = Object.assign(
        {
          emailOrigem: 'BBBBBB',
          templateEmail: 'BBBBBB',
          segundafeira: currentDate.format(DATE_TIME_FORMAT),
          quartafeira: currentDate.format(DATE_TIME_FORMAT),
          sextafeira: currentDate.format(DATE_TIME_FORMAT),
          domingo: currentDate.format(DATE_TIME_FORMAT),
        },
        new ReservaQuadraTenis()
      );

      const returnedFromService = Object.assign(patchObject, elemDefault);

      const expected = Object.assign(
        {
          semana: currentDate,
          segundafeira: currentDate,
          tercafeira: currentDate,
          quartafeira: currentDate,
          quintafeira: currentDate,
          sextafeira: currentDate,
          sabado: currentDate,
          domingo: currentDate,
        },
        returnedFromService
      );

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of ReservaQuadraTenis', () => {
      const returnedFromService = Object.assign(
        {
          id: 1,
          emailOrigem: 'BBBBBB',
          emailDestino: 'BBBBBB',
          templateEmail: 'BBBBBB',
          semana: currentDate.format(DATE_FORMAT),
          segundafeira: currentDate.format(DATE_TIME_FORMAT),
          tercafeira: currentDate.format(DATE_TIME_FORMAT),
          quartafeira: currentDate.format(DATE_TIME_FORMAT),
          quintafeira: currentDate.format(DATE_TIME_FORMAT),
          sextafeira: currentDate.format(DATE_TIME_FORMAT),
          sabado: currentDate.format(DATE_TIME_FORMAT),
          domingo: currentDate.format(DATE_TIME_FORMAT),
        },
        elemDefault
      );

      const expected = Object.assign(
        {
          semana: currentDate,
          segundafeira: currentDate,
          tercafeira: currentDate,
          quartafeira: currentDate,
          quintafeira: currentDate,
          sextafeira: currentDate,
          sabado: currentDate,
          domingo: currentDate,
        },
        returnedFromService
      );

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toContainEqual(expected);
    });

    it('should delete a ReservaQuadraTenis', () => {
      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult);
    });

    describe('addReservaQuadraTenisToCollectionIfMissing', () => {
      it('should add a ReservaQuadraTenis to an empty array', () => {
        const reservaQuadraTenis: IReservaQuadraTenis = { id: 123 };
        expectedResult = service.addReservaQuadraTenisToCollectionIfMissing([], reservaQuadraTenis);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(reservaQuadraTenis);
      });

      it('should not add a ReservaQuadraTenis to an array that contains it', () => {
        const reservaQuadraTenis: IReservaQuadraTenis = { id: 123 };
        const reservaQuadraTenisCollection: IReservaQuadraTenis[] = [
          {
            ...reservaQuadraTenis,
          },
          { id: 456 },
        ];
        expectedResult = service.addReservaQuadraTenisToCollectionIfMissing(reservaQuadraTenisCollection, reservaQuadraTenis);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a ReservaQuadraTenis to an array that doesn't contain it", () => {
        const reservaQuadraTenis: IReservaQuadraTenis = { id: 123 };
        const reservaQuadraTenisCollection: IReservaQuadraTenis[] = [{ id: 456 }];
        expectedResult = service.addReservaQuadraTenisToCollectionIfMissing(reservaQuadraTenisCollection, reservaQuadraTenis);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(reservaQuadraTenis);
      });

      it('should add only unique ReservaQuadraTenis to an array', () => {
        const reservaQuadraTenisArray: IReservaQuadraTenis[] = [{ id: 123 }, { id: 456 }, { id: 50579 }];
        const reservaQuadraTenisCollection: IReservaQuadraTenis[] = [{ id: 123 }];
        expectedResult = service.addReservaQuadraTenisToCollectionIfMissing(reservaQuadraTenisCollection, ...reservaQuadraTenisArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const reservaQuadraTenis: IReservaQuadraTenis = { id: 123 };
        const reservaQuadraTenis2: IReservaQuadraTenis = { id: 456 };
        expectedResult = service.addReservaQuadraTenisToCollectionIfMissing([], reservaQuadraTenis, reservaQuadraTenis2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(reservaQuadraTenis);
        expect(expectedResult).toContain(reservaQuadraTenis2);
      });

      it('should accept null and undefined values', () => {
        const reservaQuadraTenis: IReservaQuadraTenis = { id: 123 };
        expectedResult = service.addReservaQuadraTenisToCollectionIfMissing([], null, reservaQuadraTenis, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(reservaQuadraTenis);
      });

      it('should return initial array if no ReservaQuadraTenis is added', () => {
        const reservaQuadraTenisCollection: IReservaQuadraTenis[] = [{ id: 123 }];
        expectedResult = service.addReservaQuadraTenisToCollectionIfMissing(reservaQuadraTenisCollection, undefined, null);
        expect(expectedResult).toEqual(reservaQuadraTenisCollection);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
