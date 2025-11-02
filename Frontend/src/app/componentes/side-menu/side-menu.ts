import { Component } from '@angular/core';

@Component({
  selector: 'app-side-menu',
  imports: [],
  templateUrl: './side-menu.html',
  styleUrl: './side-menu.scss',
})
export class SideMenu {

  collapsed: boolean = false;

  toggleMenu() {

    if(this.collapsed == false) {
      this.collapsed = true;
    } else {
      this.collapsed = false;
    }

    throw new Error('Method not implemented.');
  }

}
