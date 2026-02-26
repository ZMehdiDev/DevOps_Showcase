const out = document.getElementById("out");
const statusText = document.getElementById("status-text");
const statusDot = document.getElementById("status-dot");

function setStatus(kind, text) {
  statusText.textContent = text;

  const map = {
    idle: { color: "var(--warn)", ring: "rgba(244,191,79,.14)" },
    ok: { color: "var(--good)", ring: "rgba(51,209,122,.16)" },
    err: { color: "var(--bad)", ring: "rgba(255,77,109,.16)" },
    load: { color: "var(--accent2)", ring: "rgba(34,211,238,.14)" }
  };

  const s = map[kind] || map.idle;
  statusDot.style.background = s.color;
  statusDot.style.boxShadow = `0 0 0 4px ${s.ring}`;
}

function show(data) {
  out.textContent = JSON.stringify(data, null, 2);
}

async function callJson(path) {
  setStatus("load", "loading");
  out.textContent = "";

  try {
    const res = await fetch(path, { headers: { "Accept": "application/json" } });

    // Try JSON first, fallback to text
    const ct = res.headers.get("content-type") || "";
    const body = ct.includes("application/json") ? await res.json() : await res.text();

    show({
      request: { method: "GET", path },
      response: { status: res.status, ok: res.ok },
      body
    });

    setStatus(res.ok ? "ok" : "err", res.ok ? "ok" : `error ${res.status}`);
  } catch (e) {
    show({ error: String(e) });
    setStatus("err", "network error");
  }
}

document.getElementById("btn-health").addEventListener("click", () => callJson("/health"));
document.getElementById("btn-items").addEventListener("click", () => callJson("/api/items"));

document.getElementById("btn-clear").addEventListener("click", () => {
  out.textContent = "{}";
  setStatus("idle", "idle");
});

// initial state
setStatus("idle", "idle");
show({});