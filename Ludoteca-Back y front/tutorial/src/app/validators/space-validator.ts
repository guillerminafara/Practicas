import { AbstractControl, ValidationErrors } from "@angular/forms";

export function spaceValidator(control: AbstractControl): ValidationErrors | null {
  const valor = control.value;
  if (typeof valor === 'string' && valor.trim().length === 0) {
    return { spaceValidator: true }
  }
  return null
}