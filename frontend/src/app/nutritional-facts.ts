import { Nutrient } from './nutrient';

export class NutritionalFacts {
  nutrients: Map<string,Nutrient> = new Map<string, Nutrient>();

  static fromJSON(data:any): NutritionalFacts {
    let f: NutritionalFacts = new NutritionalFacts();
    data.nutrients.forEach(d => f.nutrients.set(d.tag, Object.assign(new Nutrient,d)));
    return f;
  }

  toJSON(): any {
    let nutrients: Nutrient[] = [];
    this.nutrients.forEach(n => nutrients.push(n));
    return Object.assign({},{
      nutrients: nutrients
    });
  }

  add(other:NutritionalFacts): NutritionalFacts {
    let copy: NutritionalFacts = new NutritionalFacts();
    this.nutrients.forEach(n => {
      copy.nutrients.set(n.tag, Object.assign(new Nutrient(), n));
    });
    other.nutrients.forEach(n => {
      let cnutrient : Nutrient = copy.nutrients.get(n.tag);
      if(cnutrient == null){
        cnutrient = Object.assign(new Nutrient(), n);
      } else {
        cnutrient = Object.assign(new Nutrient(), cnutrient);
        cnutrient.value += n.value;
      }
      // Nuntrients with the same tag should appear only once
      copy.nutrients.set(cnutrient.tag,cnutrient);
    });
    return copy;
  }

  multiplyBy(m: number): NutritionalFacts {
    let copy: NutritionalFacts = new NutritionalFacts();
    this.nutrients.forEach(n => {
      let c: Nutrient = Object.assign(new Nutrient(), n);
      c.value *= m;
      copy.nutrients.set(c.tag,c);
    });
    return copy;
  }

  energy(): Nutrient {
    return this.nutrients.get('ENERC_KCAL');
  }

  protein(): Nutrient {
    return this.nutrients.get('PROCNT');
  }

  carbs(): Nutrient {
    return this.nutrients.get('CHOCDF');
  }

  fat(): Nutrient {
    return this.nutrients.get('FAT');
  }

  fiber(): Nutrient {
    return this.nutrients.get('FIBTG');
  }

  starch(): Nutrient {
    return this.nutrients.get('STARCH');
  }

  sugars(): Nutrient {
    return this.nutrients.get('SUGAR');
  }

  cystine(): Nutrient {
    return this.nutrients.get('CYS_G');
  }

  histidine(): Nutrient {
    return this.nutrients.get('HISTN_G');
  }

  isoleucine(): Nutrient {
    return this.nutrients.get('ILE_G');
  }

  leucine(): Nutrient {
    return this.nutrients.get('LEU_G');
  }

  lysine(): Nutrient {
    return this.nutrients.get('LYS_G');
  }

  methionine(): Nutrient {
    return this.nutrients.get('MET_G');
  }

  phenylalanine(): Nutrient {
    return this.nutrients.get('PHE_G');
  }

  threonine(): Nutrient {
    return this.nutrients.get('THR_G');
  }

  tryptophan(): Nutrient {
    return this.nutrients.get('TRP_G');
  }

  tyrosine(): Nutrient {
    return this.nutrients.get('TYR_G');
  }

  valine(): Nutrient {
    return this.nutrients.get('VAL_G');
  }

  thiamin(): Nutrient {
    return this.nutrients.get('THIA');
  }

  riboflavin(): Nutrient {
    return this.nutrients.get('RIBF');
  }

  niacin(): Nutrient {
    return this.nutrients.get('NIA');
  }

  pantothenicAcid():Nutrient {
    return this.nutrients.get('PANTAC');
  }

  b6(): Nutrient {
    return this.nutrients.get('VITB6A');
  }

  b12(): Nutrient {
    return this.nutrients.get('VITB12');
  }

  folate(): Nutrient {
    return this.nutrients.get('FOL');
  }

  vitA(): Nutrient {
    return this.nutrients.get('VITA_IU');
  }

  vitC(): Nutrient {
    return this.nutrients.get('VITC');
  }

  vitD(): Nutrient {
    return this.nutrients.get('VITD');
  }

  vitE(): Nutrient {
    return this.nutrients.get('TOCPHA');
  }

  vitK(): Nutrient {
    return this.nutrients.get('VITK1');
  }

  calcium(): Nutrient {
    return this.nutrients.get('CA');
  }

  copper(): Nutrient {
    return this.nutrients.get('CU');
  }

  iron(): Nutrient {
    return this.nutrients.get('FE');
  }

  magnesium(): Nutrient {
    return this.nutrients.get('MG');
  }

  manganese(): Nutrient {
    return this.nutrients.get('MN');
  }

  phosphorus(): Nutrient {
    return this.nutrients.get('P');
  }

  potassium(): Nutrient {
    return this.nutrients.get('K');
  }

  selenium(): Nutrient {
    return this.nutrients.get('SE');
  }

  sodium(): Nutrient {
    return this.nutrients.get('NA');
  }

  zinc(): Nutrient {
    return this.nutrients.get('ZN');
  }

  monounsaturated(): Nutrient {
    return this.nutrients.get('FAMS');
  }

  polyunsaturated(): Nutrient {
    return this.nutrients.get('FAPU');
  }

  saturated(): Nutrient {
    return this.nutrients.get('FASAT');
  }

  trans(): Nutrient {
    return this.nutrients.get('FATRN');
  }

  cholesterol(): Nutrient {
    return this.nutrients.get('CHOLE');
  }

}
