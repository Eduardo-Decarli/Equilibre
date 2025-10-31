import { Component } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';
import { MatDividerModule } from '@angular/material/divider';
import { MatIconModule } from '@angular/material/icon';

@Component({
  selector: 'app-navegation',
  imports: [MatButtonModule, MatDividerModule, MatIconModule],
  templateUrl: './navegation.html',
  styleUrl: './navegation.scss',
})
export class Navegation {}
