import { Component, OnInit } from '@angular/core';
import { CreditoService } from '../services/credito.service';
import { Credito } from '../models/credito.model';

@Component({
  selector: 'app-credito-consulta',
  templateUrl: './credito-consulta.component.html',
  styleUrls: ['./credito-consulta.component.css']
})
export class CreditoConsultaComponent implements OnInit {
  creditos: Credito[] = [];
  loading = false;
  error = '';
  numeroNfse = '';
  numeroCredito = '';
  tipoConsulta = 'nfse'; // 'nfse' ou 'credito'

  constructor(private creditoService: CreditoService) { }

  ngOnInit(): void {
  }

  onTipoConsultaChange(): void {
    this.limparResultados();
  }

  buscarCreditos(): void {
    if (this.tipoConsulta === 'nfse' && !this.numeroNfse.trim()) {
      this.error = 'Por favor, informe o número da NFS-e';
      return;
    }

    if (this.tipoConsulta === 'credito' && !this.numeroCredito.trim()) {
      this.error = 'Por favor, informe o número do crédito';
      return;
    }

    this.loading = true;
    this.error = '';
    this.creditos = [];

    if (this.tipoConsulta === 'nfse') {
      this.creditoService.getCreditosByNumeroNfse(this.numeroNfse.trim()).subscribe({
        next: (data) => {
          this.creditos = data;
          this.loading = false;
          if (data.length === 0) {
            this.error = 'Nenhum crédito encontrado para a NFS-e informada';
          }
        },
        error: (err) => {
          this.loading = false;
          this.error = 'Erro ao buscar créditos. Verifique se a API está funcionando.';
          console.error('Erro:', err);
        }
      });
    } else {
      this.creditoService.getCreditoByNumeroCredito(this.numeroCredito.trim()).subscribe({
        next: (data) => {
          this.creditos = [data];
          this.loading = false;
        },
        error: (err) => {
          this.loading = false;
          if (err.status === 404) {
            this.error = 'Crédito não encontrado';
          } else {
            this.error = 'Erro ao buscar crédito. Verifique se a API está funcionando.';
          }
          console.error('Erro:', err);
        }
      });
    }
  }

  limparResultados(): void {
    this.creditos = [];
    this.error = '';
    this.numeroNfse = '';
    this.numeroCredito = '';
  }

  formatarValor(valor: number): string {
    return new Intl.NumberFormat('pt-BR', {
      style: 'currency',
      currency: 'BRL'
    }).format(valor);
  }

  formatarData(data: string): string {
    return new Date(data + 'T00:00:00').toLocaleDateString('pt-BR');
  }

  formatarSimplesNacional(simplesNacional: boolean): string {
    return simplesNacional ? 'Sim' : 'Não';
  }
}

