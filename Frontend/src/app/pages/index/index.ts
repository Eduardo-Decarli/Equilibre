import { Component } from '@angular/core';
import { SideMenu } from '../../componentes/side-menu/side-menu';

@Component({
  selector: 'app-index',
  imports: [SideMenu],
  templateUrl: './index.html',
  styleUrl: './index.scss',
})
export class Index {}
