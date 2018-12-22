export class Macros {
  calories: number = 0;
  protein: number = 0;
  carbs: number = 0 ;
  fat: number = 0;

  add(other:Macros): Macros {
    let copy: Macros = Object.assign(new Macros(), this);
    copy.calories += other.calories;
    copy.protein += other.protein;
    copy.carbs += other.carbs;
    copy.fat += other.fat;
    return copy;
  }

  multiplyBy(m: number): Macros {
    let copy: Macros = Object.assign(new Macros(), this);
    copy.calories *= m;
    copy.protein *= m;
    copy.carbs *= m;
    copy.fat *= m;
    return copy;
  }
}
