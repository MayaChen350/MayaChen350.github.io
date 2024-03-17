const logos = Array.from(document.querySelectorAll(".logo"));

const click = (element, unclick = false) =>
  !unclick
    ? element.classList.add("clicked")
    : element.classList.remove("clicked");

logos.forEach((logo, _, logos) =>
  logo.addEventListener("click", () => {
    logos.forEach((l) => click(l, true));
    click(logo);
  })
);
