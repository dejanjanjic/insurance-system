import { Component, inject, OnInit } from '@angular/core';
import { RouterOutlet, RouterModule } from '@angular/router';
import { AuthService } from './services/auth.service';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, RouterModule],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css',
})
export class AppComponent implements OnInit {
  private authService: AuthService = inject(AuthService);
  public test = 'aa';

  ngOnInit(): void {
    this.authService.getTest().subscribe({
      next: (result: string) => {
        console.log('OP');
        this.test = result;
      },
      error: (err) => {
        console.error('Error during test:', err.message);
      },
    });
  }
}
