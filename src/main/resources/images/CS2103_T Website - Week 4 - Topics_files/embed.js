(function (g) {
    var window = this;
    var Ira = function (a, b) {
            var c = (b - a.o) / (a.u - a.o);
            if (0 >= c) return 0;
            if (1 <= c) return 1;
            for (var d = 0, e = 1, f = 0, k = 0; 8 > k; k++) {
                f = g.sn(a, c);
                var l = (g.sn(a, c + 1E-6) - f) / 1E-6;
                if (1E-6 > Math.abs(f - b)) return c;
                if (1E-6 > Math.abs(l)) break; else f < b ? d = c : e = c, c -= (f - b) / l
            }
            for (k = 0; 1E-6 < Math.abs(f - b) && 8 > k; k++) f < b ? (d = c, c = (c + e) / 2) : (e = c, c = (c + d) / 2), f = g.sn(a, c);
            return c
        }, Jra = function () {
            return g.W ? {
                D: "div",
                X: ["ytp-icon", "ytp-icon-watermark"]
            } : {
                D: "svg", N: {height: "100%", version: "1.1", viewBox: "0 0 110 26", width: "100%"}, K: [{
                    D: "path",
                    La: !0,
                    I: "ytp-svg-fill",
                    N: {d: "M 16.68,.99 C 13.55,1.03 7.02,1.16 4.99,1.68 c -1.49,.4 -2.59,1.6 -2.99,3 -0.69,2.7 -0.68,8.31 -0.68,8.31 0,0 -0.01,5.61 .68,8.31 .39,1.5 1.59,2.6 2.99,3 2.69,.7 13.40,.68 13.40,.68 0,0 10.70,.01 13.40,-0.68 1.5,-0.4 2.59,-1.6 2.99,-3 .69,-2.7 .68,-8.31 .68,-8.31 0,0 .11,-5.61 -0.68,-8.31 -0.4,-1.5 -1.59,-2.6 -2.99,-3 C 29.11,.98 18.40,.99 18.40,.99 c 0,0 -0.67,-0.01 -1.71,0 z m 72.21,.90 0,21.28 2.78,0 .31,-1.37 .09,0 c .3,.5 .71,.88 1.21,1.18 .5,.3 1.08,.40 1.68,.40 1.1,0 1.99,-0.49 2.49,-1.59 .5,-1.1 .81,-2.70 .81,-4.90 l 0,-2.40 c 0,-1.6 -0.11,-2.90 -0.31,-3.90 -0.2,-0.89 -0.5,-1.59 -1,-2.09 -0.5,-0.4 -1.10,-0.59 -1.90,-0.59 -0.59,0 -1.18,.19 -1.68,.49 -0.49,.3 -1.01,.80 -1.21,1.40 l 0,-7.90 -3.28,0 z m -49.99,.78 3.90,13.90 .18,6.71 3.31,0 0,-6.71 3.87,-13.90 -3.37,0 -1.40,6.31 c -0.4,1.89 -0.71,3.19 -0.81,3.99 l -0.09,0 c -0.2,-1.1 -0.51,-2.4 -0.81,-3.99 l -1.37,-6.31 -3.40,0 z m 29.59,0 0,2.71 3.40,0 0,17.90 3.28,0 0,-17.90 3.40,0 c 0,0 .00,-2.71 -0.09,-2.71 l -9.99,0 z m -53.49,5.12 8.90,5.18 -8.90,5.09 0,-10.28 z m 89.40,.09 c -1.7,0 -2.89,.59 -3.59,1.59 -0.69,.99 -0.99,2.60 -0.99,4.90 l 0,2.59 c 0,2.2 .30,3.90 .99,4.90 .7,1.1 1.8,1.59 3.5,1.59 1.4,0 2.38,-0.3 3.18,-1 .7,-0.7 1.09,-1.69 1.09,-3.09 l 0,-0.5 -2.90,-0.21 c 0,1 -0.08,1.6 -0.28,2 -0.1,.4 -0.5,.62 -1,.62 -0.3,0 -0.61,-0.11 -0.81,-0.31 -0.2,-0.3 -0.30,-0.59 -0.40,-1.09 -0.1,-0.5 -0.09,-1.21 -0.09,-2.21 l 0,-0.78 5.71,-0.09 0,-2.62 c 0,-1.6 -0.10,-2.78 -0.40,-3.68 -0.2,-0.89 -0.71,-1.59 -1.31,-1.99 -0.7,-0.4 -1.48,-0.59 -2.68,-0.59 z m -50.49,.09 c -1.09,0 -2.01,.18 -2.71,.68 -0.7,.4 -1.2,1.12 -1.49,2.12 -0.3,1 -0.5,2.27 -0.5,3.87 l 0,2.21 c 0,1.5 .10,2.78 .40,3.78 .2,.9 .70,1.62 1.40,2.12 .69,.5 1.71,.68 2.81,.78 1.19,0 2.08,-0.28 2.78,-0.68 .69,-0.4 1.09,-1.09 1.49,-2.09 .39,-1 .49,-2.30 .49,-3.90 l 0,-2.21 c 0,-1.6 -0.2,-2.87 -0.49,-3.87 -0.3,-0.89 -0.8,-1.62 -1.49,-2.12 -0.7,-0.5 -1.58,-0.68 -2.68,-0.68 z m 12.18,.09 0,11.90 c -0.1,.3 -0.29,.48 -0.59,.68 -0.2,.2 -0.51,.31 -0.81,.31 -0.3,0 -0.58,-0.10 -0.68,-0.40 -0.1,-0.3 -0.18,-0.70 -0.18,-1.40 l 0,-10.99 -3.40,0 0,11.21 c 0,1.4 .18,2.39 .68,3.09 .49,.7 1.21,1 2.21,1 1.4,0 2.48,-0.69 3.18,-2.09 l .09,0 .31,1.78 2.59,0 0,-14.99 c 0,0 -3.40,.00 -3.40,-0.09 z m 17.31,0 0,11.90 c -0.1,.3 -0.29,.48 -0.59,.68 -0.2,.2 -0.51,.31 -0.81,.31 -0.3,0 -0.58,-0.10 -0.68,-0.40 -0.1,-0.3 -0.21,-0.70 -0.21,-1.40 l 0,-10.99 -3.40,0 0,11.21 c 0,1.4 .21,2.39 .71,3.09 .5,.7 1.18,1 2.18,1 1.39,0 2.51,-0.69 3.21,-2.09 l .09,0 .28,1.78 2.62,0 0,-14.99 c 0,0 -3.40,.00 -3.40,-0.09 z m 20.90,2.09 c .4,0 .58,.11 .78,.31 .2,.3 .30,.59 .40,1.09 .1,.5 .09,1.21 .09,2.21 l 0,1.09 -2.5,0 0,-1.09 c 0,-1 -0.00,-1.71 .09,-2.21 0,-0.4 .11,-0.8 .31,-1 .2,-0.3 .51,-0.40 .81,-0.40 z m -50.49,.12 c .5,0 .8,.18 1,.68 .19,.5 .28,1.30 .28,2.40 l 0,4.68 c 0,1.1 -0.08,1.90 -0.28,2.40 -0.2,.5 -0.5,.68 -1,.68 -0.5,0 -0.79,-0.18 -0.99,-0.68 -0.2,-0.5 -0.31,-1.30 -0.31,-2.40 l 0,-4.68 c 0,-1.1 .11,-1.90 .31,-2.40 .2,-0.5 .49,-0.68 .99,-0.68 z m 39.68,.09 c .3,0 .61,.10 .81,.40 .2,.3 .27,.67 .37,1.37 .1,.6 .12,1.51 .12,2.71 l .09,1.90 c 0,1.1 .00,1.99 -0.09,2.59 -0.1,.6 -0.19,1.08 -0.49,1.28 -0.2,.3 -0.50,.40 -0.90,.40 -0.3,0 -0.51,-0.08 -0.81,-0.18 -0.2,-0.1 -0.39,-0.29 -0.59,-0.59 l 0,-8.5 c .1,-0.4 .29,-0.7 .59,-1 .3,-0.3 .60,-0.40 .90,-0.40 z"}
                }]
            }
        },
        Kra = function () {
            return g.W ? {
                D: "div",
                X: ["ytp-icon", "ytp-icon-youtube-logo-redirect"]
            } : {
                D: "svg",
                N: {fill: "#fff", height: "100%", viewBox: "0 0 24 24", width: "100%"},
                K: [{D: "path", N: {d: "M0 0h24v24H0V0z", fill: "none"}}, {
                    D: "path",
                    N: {d: "M21.58 7.19c-.23-.86-.91-1.54-1.77-1.77C18.25 5 12 5 12 5s-6.25 0-7.81.42c-.86.23-1.54.91-1.77 1.77C2 8.75 2 12 2 12s0 3.25.42 4.81c.23.86.91 1.54 1.77 1.77C5.75 19 12 19 12 19s6.25 0 7.81-.42c.86-.23 1.54-.91 1.77-1.77C22 15.25 22 12 22 12s0-3.25-.42-4.81zM10 15V9l5.2 3-5.2 3z"}
                }]
            }
        }, M4 = function (a) {
            g.A.call(this);
            this.B = a;
            this.A = new g.rn(0, 0, .4, 0, .2, 1, 1, 1);
            this.u = new g.Ln(this.w, window, this);
            g.B(this, this.u)
        }, N4 = function (a) {
            g.U.call(this, {
                D: "div",
                I: "ytp-related-on-error-overlay"
            });
            this.T = this.A = 0;
            this.u = a;
            this.U = new g.rr(this);
            g.B(this, this.U);
            a = a.P();
            this.C = [];
            this.w = [];
            this.F = 0;
            this.aa = a.u;
            this.O = new g.U({D: "h2", I: "ytp-related-title", W: "{{title}}"});
            g.B(this, this.O);
            this.O.ca(this.element);
            this.G = new g.U({D: "div", I: "ytp-suggestions"});
            g.B(this, this.G);
            this.G.ca(this.element);
            this.J = new g.U({
                D: "button",
                X: ["ytp-button", "ytp-previous"],
                N: {"aria-label": "Show previous suggested videos"},
                K: [g.bM()]
            });
            g.B(this, this.J);
            this.J.ca(this.element);
            this.J.ha("click",
                this.rH, this);
            this.ea = new M4((0, g.x)(this.Ay, this));
            g.B(this, this.ea);
            this.Z = this.B = 0;
            this.V = !0;
            for (var b = 0; 16 > b; b++) {
                var c = new g.U({
                    D: "a",
                    I: "ytp-suggestion-link",
                    N: {href: "{{link}}", target: a.A, "aria-label": "{{aria_label}}"},
                    K: [{
                        D: "div",
                        I: "ytp-suggestion-image",
                        K: [{D: "div", N: {"data-is-live": "{{is_live}}"}, I: "ytp-suggestion-duration", W: "{{duration}}"}]
                    }, {D: "div", I: "ytp-suggestion-title", N: {title: "{{hover_title}}"}, W: "{{title}}"}, {
                        D: "div",
                        I: "ytp-suggestion-author",
                        W: "{{views_or_author}}"
                    }]
                });
                g.B(this,
                    c);
                c.ca(this.G.element);
                var d = c.o["ytp-suggestion-link"];
                g.mh(d, "transitionDelay", b / 20 + "s");
                this.U.L(d, "click", g.La(this.sH, b));
                this.C.push(c)
            }
            this.H = new g.U({
                D: "button",
                X: ["ytp-button", "ytp-next"],
                N: {"aria-label": "Show more suggested videos"},
                K: [g.cM()]
            });
            g.B(this, this.H);
            this.H.ca(this.element);
            this.H.ha("click", this.qH, this);
            this.U.L(this.u, "videodatachange", this.zy);
            Lra(this, g.jL(this.u).getPlayerSize());
            this.zy();
            this.show()
        }, Lra = function (a, b, c) {
            var d = a.u.P(), e = 16 / 9, f = 650 <= b.width, k = 480 > b.width ||
                290 > b.height, l = Math.min(a.w.length, a.C.length);
            if (150 >= Math.min(b.width, b.height) || 0 == l || !d.Ia) a.hide(); else {
                var m;
                if (f) {
                    var n = m = 28;
                    a.A = 16
                } else n = m = 8, a.A = 8;
                if (k) {
                    var q = 6;
                    f = 14;
                    var t = 12;
                    k = 24;
                    d = 12
                } else q = 8, f = 18, t = 16, k = 36, d = 16;
                b = b.width - (48 + m + n);
                m = Math.ceil(b / 150);
                m = Math.min(3, m);
                m = b / m - a.A;
                n = Math.floor(m / e);
                c && n + 100 > c && 50 < m && (n = Math.max(c, 50 / e), m = Math.ceil(b / (e * (n - 100) + a.A)), m = b / m - a.A, n = Math.floor(m / e));
                50 > m || g.oL(a.u) ? a.hide() : a.show();
                for (c = 0; c < l; c++) {
                    e = a.C[c];
                    var u = e.o["ytp-suggestion-image"];
                    u.style.width = m + "px";
                    u.style.height = n + "px";
                    e.o["ytp-suggestion-title"].style.width =
                        m + "px";
                    e.o["ytp-suggestion-author"].style.width = m + "px";
                    e = e.o["ytp-suggestion-duration"];
                    e.style.display = e && 100 > m ? "none" : ""
                }
                l = f + q + t + 4;
                a.T = l + d + (n - k) / 2;
                a.G.element.style.height = n + l + "px";
                a.Z = m;
                a.F = b;
                a.B = 0;
                a.Ay(0);
                O4(a)
            }
        }, Mra = function (a, b) {
            var c = g.nd(b, a.F - a.w.length * (a.Z + a.A), 0);
            a.ea.start(a.B, c, 1E3);
            a.B = c;
            O4(a)
        }, O4 = function (a) {
            a.H.element.style.bottom = a.T + "px";
            a.J.element.style.bottom = a.T + "px";
            var b = a.B, c = a.F - a.w.length * (a.Z + a.A);
            g.K(a.element, "ytp-scroll-min", 0 <= b);
            g.K(a.element, "ytp-scroll-max", b <= c)
        }, Nra = function (a) {
            for (var b = 0; b < a.w.length; b++) {
                var c = b, d = a.w[b].xa();
                c = a.C[c];
                var e = d.shortViewCount ? d.shortViewCount : d.author, f = d.Gi();
                g.sx(a.u.P()) && (f = g.Xg(f, g.CD({}, "emb_rel_err")));
                c.element.style.display = "";
                var k = c.o["ytp-suggestion-title"];
                g.pn.test(d.title) ? k.dir = "rtl" : g.H1.test(d.title) && (k.dir = "ltr");
                k = c.o["ytp-suggestion-author"];
                g.pn.test(e) ? k.dir = "rtl" : g.H1.test(e) && (k.dir = "ltr");
                k = void 0;
                d.ua ? k = "Live" : k = d.lengthSeconds ? g.UL(d.lengthSeconds) : "";
                c.update({
                    views_or_author: e,
                    duration: k,
                    link: f,
                    hover_title: d.title,
                    title: d.title,
                    aria_label: d.Rj || null,
                    is_live: d.ua
                });
                c = c.o["ytp-suggestion-image"];
                d = d.yc();
                c.style.backgroundImage = d ? "url(" + d + ")" : ""
            }
            for (; b < a.C.length; b++) a.C[b].element.style.display = "none";
            O4(a)
        }, P4 = function (a) {
            g.OM.call(this, a);
            a = a.P();
            a = new g.U({
                D: "a",
                I: "ytp-small-redirect",
                N: {href: g.$x(a), target: a.A, "aria-label": "Visit YouTube to search for more videos"},
                K: [Kra()]
            });
            a.ca(this.element);
            g.B(this, a);
            this.w = new N4(this.api);
            this.w.ca(this.element);
            g.B(this, this.w);
            this.C = 1;
            this.u(g.jL(this.api).getPlayerSize())
        }, Ora = function (a, b) {
            a.o["ytp-error-content"].style.paddingTop = "0px";
            var c = a.o["ytp-error-content"], d = c.clientHeight;
            Lra(a.w, b, b.height - d);
            if (a.api.P().B) {
                var e = g.kM(), f = g.W ? {D: "div", X: ["ytp-icon", "ytp-icon-youtube-logo-redirect-large"]} : {
                        D: "svg",
                        N: {fill: "#fff", viewBox: "0 0 24 24"},
                        K: [{D: "path", N: {d: "M0 0h24v24H0V0z", fill: "none"}}, {
                            D: "path",
                            N: {d: "M21.58 7.19c-.23-.86-.91-1.54-1.77-1.77C18.25 5 12 5 12 5s-6.25 0-7.81.42c-.86.23-1.54.91-1.77 1.77C2 8.75 2 12 2 12s0 3.25.42 4.81c.23.86.91 1.54 1.77 1.77C5.75 19 12 19 12 19s6.25 0 7.81-.42c.86-.23 1.54-.91 1.77-1.77C22 15.25 22 12 22 12s0-3.25-.42-4.81zM10 15V9l5.2 3-5.2 3z"}
                        }]
                    },
                    k = 1;
                480 > b.width || 290 > b.height ? (e = {
                    D: "div",
                    X: ["ytp-icon", "ytp-icon-error-exclamation-small"]
                }, f = Kra(), k = 0) : 650 <= b.width && (e = {
                    D: "div",
                    X: ["ytp-icon", "ytp-icon-error-exclamation-large"]
                }, f = g.W ? {D: "div", X: ["ytp-icon", "ytp-icon-youtube-logo-redirect-extra-large"]} : {
                    D: "svg",
                    N: {fill: "#fff", height: "100%", viewBox: "0 0 24 24", width: "100%"},
                    K: [{D: "path", N: {d: "M0 0h24v24H0V0z", fill: "none"}}, {
                        D: "path",
                        N: {d: "M21.58 7.19c-.23-.86-.91-1.54-1.77-1.77C18.25 5 12 5 12 5s-6.25 0-7.81.42c-.86.23-1.54.91-1.77 1.77C2 8.75 2 12 2 12s0 3.25.42 4.81c.23.86.91 1.54 1.77 1.77C5.75 19 12 19 12 19s6.25 0 7.81-.42c.86-.23 1.54-.91 1.77-1.77C22 15.25 22 12 22 12s0-3.25-.42-4.81zM10 15V9l5.2 3-5.2 3z"}
                    }]
                },
                    k = 2);
                if (k !== a.C) {
                    e = {"ytp-error-icon-container": e, "ytp-small-redirect": f};
                    f = g.p(Object.keys(e));
                    for (var l = f.next(); !l.done; l = f.next()) {
                        l = l.value;
                        var m = g.Dd(l, a.element);
                        g.Sd(m);
                        (new g.U(e[l])).ca(m)
                    }
                    a.C = k
                }
            }
            c.style.paddingTop = (b.height - a.w.element.clientHeight) / 2 - d / 2 + "px"
        }, Q4 = function (a) {
            g.U.call(this, {
                D: "div",
                I: "ytp-muted-autoplay-endscreen-overlay",
                K: [{
                    D: "div",
                    I: "ytp-muted-autoplay-end-panel",
                    K: [{D: "div", I: "ytp-muted-autoplay-end-text", W: "{{text}}"}]
                }]
            });
            this.u = a;
            this.A = this.o["ytp-muted-autoplay-end-panel"];
            this.w = new g.BM(a);
            g.B(this, this.w);
            this.w.ca(this.A, 0);
            g.GL(a, this.element, this, 52428);
            this.L(a, "presentingplayerstatechange", this.C);
            this.ha("click", this.B);
            this.hide()
        }, R4 = function (a) {
            g.U.call(this, {
                D: "div",
                I: "ytp-muted-autoplay-overlay", K: [{
                    D: "div", I: "ytp-muted-autoplay-bottom-buttons", K: [{
                        D: "button", X: ["ytp-muted-autoplay-equalizer", "ytp-button"], K: [{
                            D: "div",
                            X: ["ytp-muted-autoplay-equalizer-icon"],
                            K: [g.W ? {D: "div", X: ["ytp-icon", "ytp-icon-equalizer-animation"]} : {
                                D: "svg", N: {height: "100%", version: "1.1", viewBox: "-4 -4 24 24", width: "100%"}, K: [{
                                    D: "g",
                                    N: {fill: "#fff"},
                                    K: [{
                                        D: "rect",
                                        I: "ytp-equalizer-bar-left",
                                        N: {height: "9", width: "4", x: "1", y: "7"}
                                    }, {
                                        D: "rect", I: "ytp-equalizer-bar-middle", N: {
                                            height: "14", width: "4",
                                            x: "6", y: "2"
                                        }
                                    }, {
                                        D: "rect",
                                        I: "ytp-equalizer-bar-right",
                                        N: {height: "12", width: "4", x: "11", y: "4"}
                                    }]
                                }]
                            }]
                        }]
                    }]
                }]
            });
            this.u = a;
            this.bottomButtons = this.o["ytp-muted-autoplay-bottom-buttons"];
            this.A = this.o["ytp-muted-autoplay-equalizer"];
            this.B = new g.L(this.C, 4E3, this);
            g.GL(a, this.element, this, 39306);
            this.L(a, "presentingplayerstatechange", this.w);
            this.L(a, "onMutedAutoplayStarts", this.w);
            this.ha("click", this.F);
            this.hide()
        }, S4 = function (a, b) {
            g.U.call(this, {
                D: "div",
                I: "ytp-pause-overlay"
            });
            this.u = a;
            this.aa = b;
            this.A = new g.rr(this);
            g.B(this, this.A);
            this.V = new g.YL(this, 1E3, !1, 100);
            g.B(this, this.V);
            var c = a.P();
            "0" == c.controlsType && g.J(g.kL(a), "ytp-pause-overlay-controls-hidden");
            this.C = [];
            this.w = [];
            this.F = 0;
            this.ea = c.u;
            this.Z = !1;
            this.oa = 0;
            this.T = new g.U({D: "h2", I: "ytp-related-title", W: "{{title}}"});
            g.B(this, this.T);
            this.T.ca(this.element);
            this.G = new g.U({D: "div", I: "ytp-suggestions"});
            g.B(this, this.G);
            this.G.ca(this.element);
            this.O = new g.U({
                D: "button", X: ["ytp-button",
                    "ytp-previous"], N: {"aria-label": "Show previous suggested videos"}, K: [g.bM()]
            });
            g.B(this, this.O);
            this.O.ca(this.element);
            this.O.ha("click", this.lH, this);
            var d = g.Fv || g.ph ? {style: "will-change: opacity"} : null;
            this.da = new M4((0, g.x)(this.yy, this));
            g.B(this, this.da);
            for (var e = this.ka = this.U = this.B = 0; 16 > e; e++) {
                var f = new g.U({
                    D: "a",
                    I: "ytp-suggestion-link",
                    N: {href: "{{link}}", target: c.A, "aria-label": "{{aria_label}}"},
                    K: [{D: "div", I: "ytp-suggestion-image"}, {
                        D: "div", I: "ytp-suggestion-overlay", N: d, K: [{
                            D: "div",
                            I: "ytp-suggestion-title", W: "{{title}}"
                        }, {D: "div", I: "ytp-suggestion-author", W: "{{author_and_views}}"}, {
                            D: "div",
                            N: {"data-is-live": "{{is_live}}"},
                            I: "ytp-suggestion-duration",
                            W: "{{duration}}"
                        }]
                    }]
                });
                g.B(this, f);
                f.ca(this.G.element);
                var k = f.o["ytp-suggestion-link"];
                g.mh(k, "transitionDelay", e / 20 + "s");
                this.A.L(k, "click", g.La(this.mH, e));
                this.C.push(f)
            }
            this.J = new g.U({
                D: "button",
                X: ["ytp-button", "ytp-next"],
                N: {"aria-label": "Show more suggested videos"},
                K: [g.cM()]
            });
            g.B(this, this.J);
            this.J.ca(this.element);
            this.J.ha("click", this.kH, this);
            c = new g.U({
                D: "button",
                X: ["ytp-button", "ytp-collapse"],
                N: {"aria-label": "Close"},
                K: [g.W ? {D: "div", X: ["ytp-icon", "ytp-icon-small-close"]} : {
                    D: "svg",
                    N: {height: "100%", viewBox: "0 0 16 16", width: "100%"},
                    K: [{D: "path", N: {d: "M13 4L12 3 8 7 4 3 3 4 7 8 3 12 4 13 8 9 12 13 13 12 9 8z", fill: "#fff"}}]
                }]
            });
            g.B(this, c);
            c.ca(this.element);
            c.ha("click", this.lK, this);
            this.H = new g.U({D: "button", X: ["ytp-button", "ytp-expand"], W: "More videos"});
            g.B(this, this.H);
            this.H.ca(this.element);
            this.H.ha("click",
                this.mK, this);
            this.A.L(this.u, "appresize", this.Ur);
            this.A.L(this.u, "fullscreentoggled", this.nH);
            this.A.L(this.u, "presentingplayerstatechange", this.oH);
            this.A.L(this.u, "videodatachange", this.pH);
            this.Ur(g.jL(this.u).getPlayerSize())
        }, Pra = function (a, b) {
            var c = g.nd(b, a.F - a.w.length * (a.U + 8), 0);
            a.da.start(a.B, c, 1E3);
            a.B = c;
            T4(a)
        }, T4 = function (a) {
            var b = a.aa.ac();
            b = a.ka / 2 + (b ? 32 : 16);
            a.J.element.style.bottom = b + "px";
            a.O.element.style.bottom = b + "px";
            b = a.B;
            var c = a.F - a.w.length * (a.U + 8);
            g.K(a.element, "ytp-scroll-min", 0 <= b);
            g.K(a.element, "ytp-scroll-max", b <= c)
        }, Qra = function (a) {
            for (var b = 0; b < a.w.length; b++) {
                var c = a.w[b].xa(), d = a.C[b], e = c.shortViewCount ? c.author + " \u2022 " + c.shortViewCount : c.author;
                d.element.style.display = "";
                g.G1.test(c.title) && (d.o["ytp-suggestion-title"].dir = "rtl");
                g.G1.test(e) && (d.o["ytp-suggestion-author"].dir = "rtl");
                var f = void 0;
                c.ua ? f = "Live" : f = c.lengthSeconds ? g.UL(c.lengthSeconds) : "";
                var k = c.Gi();
                g.sx(a.u.P()) && (k = g.Xg(k, g.CD({}, "emb_rel_pause")));
                d.update({
                    author_and_views: e,
                    duration: f,
                    link: k,
                    title: c.title,
                    aria_label: c.Rj || null,
                    is_live: c.ua
                });
                d = d.o["ytp-suggestion-image"];
                c = c.yc();
                d.style.backgroundImage = c ? "url(" + c + ")" : ""
            }
            for (; b < a.C.length; b++) a.C[b].element.style.display =
                "none";
            T4(a)
        }, U4 = function (a) {
            var b = a.P();
            g.U.call(this, {
                D: "a",
                X: ["ytp-watermark", "yt-uix-sessionlink"],
                N: {
                    target: b.A,
                    href: "{{url}}",
                    "aria-label": g.DM("Watch on $WEBSITE", {WEBSITE: g.Mx(b)}),
                    "data-sessionlink": "feature=player-watermark"
                },
                K: [Jra()]
            });
            this.u = a;
            this.A = null;
            this.B = !1;
            this.w = g.mL(a);
            g.GL(a, this.element, this, 76758);
            this.L(a, "videodatachange", this.Vr);
            this.L(a, "videodatachange", this.Cy);
            this.L(a, "presentingplayerstatechange", this.uH);
            this.L(a, "appresize", this.By);
            b = this.w;
            this.w != b && (this.w = b);
            this.Vr();
            this.Cy();
            this.By(g.jL(a).getPlayerSize())
        },
        V4 = function (a) {
            g.tP.call(this, a);
            this.o = a;
            this.B = new g.rr(this);
            g.B(this, this.B);
            this.load()
        }, Rra = function (a, b) {
            g.V(b, 128) ? (a.u || (a.u = new P4(a.o), g.B(a, a.u), g.EL(a.o, a.u.element, 4)), a.u.B(b.u), a.u.show()) : a.u && (a.u.dispose(), a.u = null)
        };
    g.r(M4, g.A);
    M4.prototype.start = function (a, b, c) {
        this.o = a;
        this.G = b;
        this.C = c;
        this.F = (0, g.nq)();
        this.w()
    };
    M4.prototype.w = function () {
        var a = (0, g.nq)() - this.F;
        var b = this.A;
        a = Ira(b, a / this.C);
        if (0 == a) b = b.B; else if (1 == a) b = b.G; else {
            var c = g.pd(b.B, b.C, a), d = g.pd(b.C, b.F, a);
            b = g.pd(b.F, b.G, a);
            c = g.pd(c, d, a);
            d = g.pd(d, b, a);
            b = g.pd(c, d, a)
        }
        b = g.nd(b, 0, 1);
        this.B((this.G - this.o) * b + this.o);
        1 > b && this.u.start()
    };
    g.r(N4, g.U);
    g.h = N4.prototype;
    g.h.hide = function () {
        this.V = !0;
        g.U.prototype.hide.call(this)
    };
    g.h.show = function () {
        this.V = !1;
        g.U.prototype.show.call(this)
    };
    g.h.isHidden = function () {
        return this.V
    };
    g.h.qH = function () {
        Mra(this, this.B - this.F)
    };
    g.h.rH = function () {
        Mra(this, this.B + this.F)
    };
    g.h.sH = function (a, b) {
        var c = this.w[a], d = c.Nb;
        if (g.JM(b, this.u, this.aa, d || void 0)) {
            var e = c.xa().videoId;
            c = c.getPlaylistId();
            g.rT(this.u.app, e, d, c, void 0, void 0)
        }
    };
    g.h.zy = function () {
        var a = this, b = this.u.getVideoData(), c = this.u.P();
        this.aa = b.qc ? !1 : c.u;
        if (b.suggestions) {
            var d = (0, g.Vd)(b.suggestions, function (e) {
                return e && !e.list
            });
            this.w = (0, g.Fc)(d, function (e) {
                e = g.DN(c, e);
                g.B(a, e);
                return e
            })
        } else this.w.length = 0;
        Nra(this);
        b.qc ? this.O.update({title: g.DM("More videos from $DNI_RELATED_CHANNEL", {DNI_RELATED_CHANNEL: b.author})}) : this.O.update({title: "More videos on YouTube"})
    };
    g.h.Ay = function (a) {
        this.G.element.scrollLeft = -a
    };
    g.r(P4, g.OM);
    P4.prototype.show = function () {
        g.OM.prototype.show.call(this);
        Ora(this, g.jL(this.api).getPlayerSize())
    };
    P4.prototype.u = function (a) {
        g.OM.prototype.u.call(this, a);
        Ora(this, a);
        g.K(this.element, "related-on-error-overlay-visible", !this.w.isHidden())
    };
    P4.prototype.B = function (a) {
        g.OM.prototype.B.call(this, a);
        a.uh && this.sb(g.PM(a.uh), "subreason")
    };
    g.r(Q4, g.U);
    Q4.prototype.C = function () {
        var a = g.mL(this.u), b = this.u.getVideoData();
        g.Q(this.u.P().experiments, "embeds_enable_muted_autoplay") && b.mutedAutoplay && (g.V(a, 2) && !this.Ea() ? (this.show(), this.w.show(), a = this.u.getVideoData(), this.ia("text", a.sz), g.K(this.element, "ytp-muted-autoplay-show-end-panel", !0), g.KL(this.u, this.element, this.Ea()), this.u.ma("onMutedAutoplayEnds")) : this.hide())
    };
    Q4.prototype.B = function () {
        var a = this.u.getVideoData(), b = this.u.getCurrentTime();
        a.mutedAutoplay = !1;
        a.endSeconds = NaN;
        a.je();
        this.u.loadVideoById(a.videoId, b);
        g.JL(this.u, this.element);
        this.hide()
    };
    g.r(R4, g.U);
    R4.prototype.w = function () {
        var a = g.mL(this.u), b = this.u.getVideoData(), c = this.u.P();
        b = g.Q(c.experiments, "embeds_enable_muted_autoplay") && b.mutedAutoplay;
        c = !c.B;
        !b || g.V(a, 2) ? this.hide() : this.Ea() || (g.U.prototype.show.call(this), c || (this.A.style.display = "none"), this.B.start(), g.KL(this.u, this.element, this.Ea()))
    };
    R4.prototype.C = function () {
        g.K(this.element, "ytp-muted-autoplay-hide-watermark", !0)
    };
    R4.prototype.F = function () {
        var a = this.u.getVideoData(), b = this.u.getCurrentTime();
        a.mutedAutoplay = !1;
        a.endSeconds = NaN;
        a.je();
        this.u.loadVideoById(a.videoId, b);
        g.JL(this.u, this.element)
    };
    g.r(S4, g.U);
    g.h = S4.prototype;
    g.h.hide = function () {
        g.nn(g.kL(this.u), "ytp-expand-pause-overlay");
        g.U.prototype.hide.call(this)
    };
    g.h.lK = function () {
        this.Z = !0;
        g.nn(g.kL(this.u), "ytp-expand-pause-overlay");
        this.H.focus()
    };
    g.h.mK = function () {
        this.Z = !1;
        g.J(g.kL(this.u), "ytp-expand-pause-overlay")
    };
    g.h.kH = function () {
        Pra(this, this.B - this.F)
    };
    g.h.lH = function () {
        Pra(this, this.B + this.F)
    };
    g.h.mH = function (a, b) {
        if (1E3 > (0, g.nq)() - this.oa) g.Jq(b), document.activeElement.blur(); else {
            var c = this.w[a], d = c.Nb;
            if (g.JM(b, this.u, this.ea, d || void 0)) {
                var e = c.xa().videoId;
                c = c.getPlaylistId();
                g.rT(this.u.app, e, d, c, void 0, void 0)
            }
        }
    };
    g.h.nH = function () {
        this.Ur(g.jL(this.u).getPlayerSize())
    };
    g.h.oH = function (a) {
        if (!(g.V(a.state, 1) || g.V(a.state, 16) || g.V(a.state, 32))) {
            var b = !g.Q(this.u.P().experiments, "embeds_disable_pauseoverlay_on_autoplay_blocked_killswitch") && g.V(a.state, 2048);
            !g.V(a.state, 4) || g.V(a.state, 2) || b ? this.V.hide() : this.w.length && (this.Z || (g.J(g.kL(this.u), "ytp-expand-pause-overlay"), T4(this)), this.V.show(), this.oa = (0, g.nq)())
        }
    };
    g.h.Ur = function (a) {
        var b = 16 / 9, c = this.aa.ac();
        a = a.width - (c ? 112 : 58);
        c = Math.ceil(a / (c ? 320 : 192));
        c = (a - 8 * c) / c;
        b = Math.floor(c / b);
        for (var d = 0; d < this.C.length; d++) {
            var e = this.C[d].o["ytp-suggestion-image"];
            e.style.width = c + "px";
            e.style.height = b + "px"
        }
        this.G.element.style.height = b + "px";
        this.U = c;
        this.ka = b;
        this.F = a;
        this.B = 0;
        this.yy(0);
        T4(this)
    };
    g.h.pH = function () {
        var a = this, b = this.u.P(), c = this.u.getVideoData();
        this.ea = c.qc ? !1 : b.u;
        if (c.suggestions) {
            var d = (0, g.Vd)(c.suggestions, function (e) {
                return e && !e.list
            });
            this.w = (0, g.Fc)(d, function (e) {
                e = g.DN(b, e);
                g.B(a, e);
                return e
            })
        } else this.w.length = 0;
        Qra(this);
        c.qc ? this.T.update({title: g.DM("More videos from $DNI_RELATED_CHANNEL", {DNI_RELATED_CHANNEL: c.author})}) : this.T.update({title: "More videos"})
    };
    g.h.yy = function (a) {
        this.G.element.scrollLeft = -a
    };
    g.r(U4, g.U);
    g.h = U4.prototype;
    g.h.Vr = function () {
        var a = this.u.getVideoData(), b = this.u.P();
        a = a.mutedAutoplay || b.Ka && !g.V(this.w, 2);
        g.CC(this, a);
        g.KL(this.u, this.element, a)
    };
    g.h.uH = function (a) {
        a = a.state;
        this.w != a && (this.w = a);
        this.Vr()
    };
    g.h.Cy = function () {
        if (this.u.getVideoData().videoId) {
            var a = this.u.getVideoUrl(!0, !1, !1, !0);
            this.ia("url", a);
            this.A || (this.A = this.ha("click", this.tH))
        } else this.A && (this.ia("url", null), this.Xa(this.A), this.A = null)
    };
    g.h.tH = function (a) {
        var b = this.u.getVideoUrl(!g.VL(a), !1, !0, !0);
        g.KM(b, this.u, a);
        g.JL(this.u, this.element)
    };
    g.h.By = function (a) {
        var b = this.u.P(), c = 480 > a.width;
        if (c && !this.B || !c && this.B) a = 480 > a.width && b.B ? {
            D: "div",
            X: ["ytp-icon", "ytp-icon-watermark-small"]
        } : Jra(), a = new g.U(a), b = this.o["ytp-watermark"], g.K(b, "ytp-watermark-small", c), g.Sd(b), a.ca(b), this.B = c
    };
    g.r(V4, g.tP);
    g.h = V4.prototype;
    g.h.wf = function () {
        return !1
    };
    g.h.create = function () {
        var a = this.o.P(), b = g.TC(this.o);
        a.Ia && (this.F = new S4(this.o, b), g.B(this, this.F), g.EL(this.o, this.F.element, 4));
        g.Q(a.experiments, "embeds_enable_muted_autoplay") && (this.w = new R4(this.o), g.B(this, this.w), g.EL(this.o, this.w.element, 4), this.C = new Q4(this.o), g.B(this, this.C), g.EL(this.o, this.C.element, 4));
        if (a.Ka || this.w) this.A = new U4(this.o), g.B(this, this.A), g.EL(this.o, this.A.element, 7);
        this.B.L(this.o, "appresize", this.fH);
        this.B.L(this.o, "presentingplayerstatechange", this.eH);
        this.B.L(this.o, "videodatachange", this.IO);
        this.B.L(this.o, "onMutedAutoplayStarts", this.uL);
        Rra(this, g.mL(this.o));
        g.BL(this.player, "embed")
    };
    g.h.fH = function () {
        var a = g.jL(this.o).getPlayerSize();
        this.u && this.u.u(a)
    };
    g.h.eH = function (a) {
        Rra(this, a.state)
    };
    g.h.uL = function () {
        this.o.getVideoData().mutedAutoplay && this.w && this.A && this.A.ca(this.w.bottomButtons, 0)
    };
    g.h.IO = function () {
        var a = this.o.getVideoData();
        this.A && this.w && !a.mutedAutoplay && g.Yd(this.w.element, this.A.element) && g.EL(this.o, this.A.element, 7)
    };
    g.IP.embed = V4;
})(_yt_player);
