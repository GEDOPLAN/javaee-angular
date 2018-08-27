class ListValue {
  id: number;
  value: string;
}

export class ListModel {
  available: ListValue[];
  filtered: ListValue[];
  selected: any;

  constructor(availables: ListValue[], selectedId?: any) {
    this.available = availables;
    if (selectedId) {
      if (Array.isArray(selectedId)) {
        this.selected = selectedId.map(e => availables.find(el => el.id == e));
      } else {
        this.selected = availables.find(e => e.id == selectedId);
      }
    }
  }

  filter(event) {
    this.filtered = this.available.filter(e => e.value.indexOf(event.query) != -1);
  }

  getIdObject() {
    if (Array.isArray(this.selected)) {
      return this.selected.map(e => {
        return e.id;
      });
    } else {
      return this.selected.id;
    }
  }
}
