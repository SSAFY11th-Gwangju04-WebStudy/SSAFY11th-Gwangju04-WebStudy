const fs = require("fs");
const filePath = process.platform === "linux" ? "/dev/stdin" : "input.txt";
const input = fs.readFileSync(filePath).toString().trim().split("\n");

let [nmr, items, ...ipt] = input;
let [n, m, r] = nmr
  .trim()
  .split(" ")
  .map((e) => +e);
let itemArr = items
  .trim()
  .split(" ")
  .map((e) => +e);
itemArr.unshift(0);

ipt = ipt.map((e) =>
  e
    .trim()
    .split(" ")
    .map((i) => +i)
);

let graph = new Array(n + 1).fill().map((a) => new Array(n + 1).fill(Infinity));

for (let i = 1; i < n + 1; i++) {
  graph[i][i] = 0;
}

for (let i of ipt) {
  let [a, b, l] = i;
  graph[a][b] = l;
  graph[b][a] = l;
}

for (let mid = 1; mid < n + 1; mid++) {
  for (let start = 1; start < n + 1; start++) {
    for (let end = 1; end < n + 1; end++) {
      if (graph[start][end] > graph[start][mid] + graph[mid][end] && start != end) {
        graph[start][end] = graph[start][mid] + graph[mid][end];
      }
    }
  }
}

let maxItemCnt = 0;
for (let i = 1; i < n + 1; i++) {
  let canGetItemCntAtNode = 0;
  for (let j = 1; j < n + 1; j++) {
    if (graph[i][j] <= m) {
      canGetItemCntAtNode += itemArr[j];
    }
  }
  maxItemCnt = Math.max(maxItemCnt, canGetItemCntAtNode);
}

console.log(maxItemCnt);
