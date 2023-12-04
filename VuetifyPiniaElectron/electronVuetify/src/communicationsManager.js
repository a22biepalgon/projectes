
export async function getVotacions() {
    const response = await fetch(`http://localhost:3999/votacions`)
    const data = await response.json()
    return data;
  }