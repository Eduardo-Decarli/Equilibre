import { Component, signal } from '@angular/core';
import { Index } from "./pages/index/index";

@Component({
  selector: 'app-root',
  imports: [Index],
  templateUrl: './app.html',
  styleUrl: './app.scss'
})
export class App {
  protected readonly title = signal('Frontend');
}
