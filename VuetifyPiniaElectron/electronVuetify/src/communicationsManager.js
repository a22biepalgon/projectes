
export async function getVotacions() {
    const response = await fetch(`http://electronbiel.dam.inspedralbes.cat:3473/votacions`)
    const data = await response.json()
    return data;
  }