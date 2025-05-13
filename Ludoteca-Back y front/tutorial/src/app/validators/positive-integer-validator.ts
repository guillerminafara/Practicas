import { AbstractControl, ValidationErrors } from "@angular/forms";

export function positiveIntegerValidator(control: AbstractControl): ValidationErrors | null {
    const value = control.value;
    const num = +value;
    if (!Number.isInteger(num) || num < 0) {
        return { positiveIntegerVAlidator: true }
    }
    return null;
}