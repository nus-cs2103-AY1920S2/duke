(function () {/*

 Copyright The Closure Library Authors.
 SPDX-License-Identifier: Apache-2.0
*/
    var n;

    function aa(a) {
        var b = 0;
        return function () {
            return b < a.length ? {done: !1, value: a[b++]} : {done: !0}
        }
    }

    function q(a) {
        var b = "undefined" != typeof Symbol && Symbol.iterator && a[Symbol.iterator];
        return b ? b.call(a) : {next: aa(a)}
    }

    function ba(a) {
        for (var b, c = []; !(b = a.next()).done;) c.push(b.value);
        return c
    }

    var ca = "function" == typeof Object.create ? Object.create : function (a) {
        function b() {
        }

        b.prototype = a;
        return new b
    }, da;
    if ("function" == typeof Object.setPrototypeOf) da = Object.setPrototypeOf; else {
        var ea;
        a:{
            var fa = {a: !0}, ha = {};
            try {
                ha.__proto__ = fa;
                ea = ha.a;
                break a
            } catch (a) {
            }
            ea = !1
        }
        da = ea ? function (a, b) {
            a.__proto__ = b;
            if (a.__proto__ !== b) throw new TypeError(a + " is not extensible");
            return a
        } : null
    }
    var ia = da;

    function t(a, b) {
        a.prototype = ca(b.prototype);
        a.prototype.constructor = a;
        if (ia) ia(a, b); else for (var c in b) if ("prototype" != c) if (Object.defineProperties) {
            var d = Object.getOwnPropertyDescriptor(b, c);
            d && Object.defineProperty(a, c, d)
        } else a[c] = b[c];
        a.L = b.prototype
    }

    var ja = "function" == typeof Object.defineProperties ? Object.defineProperty : function (a, b, c) {
        a != Array.prototype && a != Object.prototype && (a[b] = c.value)
    };

    function ka(a) {
        a = ["object" == typeof window && window, "object" == typeof self && self, "object" == typeof global && global, a];
        for (var b = 0; b < a.length; ++b) {
            var c = a[b];
            if (c && c.Math == Math) return c
        }
        return globalThis
    }

    var la = ka(this);

    function u(a, b) {
        if (b) {
            for (var c = la, d = a.split("."), e = 0; e < d.length - 1; e++) {
                var f = d[e];
                f in c || (c[f] = {});
                c = c[f]
            }
            d = d[d.length - 1];
            e = c[d];
            f = b(e);
            f != e && null != f && ja(c, d, {configurable: !0, writable: !0, value: f})
        }
    }

    function na(a, b, c) {
        if (null == a) throw new TypeError("The 'this' value for String.prototype." + c + " must not be null or undefined");
        if (b instanceof RegExp) throw new TypeError("First argument to String.prototype." + c + " must not be a regular expression");
        return a + ""
    }

    u("String.prototype.endsWith", function (a) {
        return a ? a : function (b, c) {
            var d = na(this, b, "endsWith");
            b += "";
            void 0 === c && (c = d.length);
            for (var e = Math.max(0, Math.min(c | 0, d.length)), f = b.length; 0 < f && 0 < e;) if (d[--e] != b[--f]) return !1;
            return 0 >= f
        }
    });
    u("String.prototype.startsWith", function (a) {
        return a ? a : function (b, c) {
            var d = na(this, b, "startsWith");
            b += "";
            for (var e = d.length, f = b.length, g = Math.max(0, Math.min(c | 0, d.length)), h = 0; h < f && g < e;) if (d[g++] != b[h++]) return !1;
            return h >= f
        }
    });

    function oa() {
        oa = function () {
        };
        la.Symbol || (la.Symbol = pa)
    }

    function qa(a, b) {
        this.f = a;
        ja(this, "description", {configurable: !0, writable: !0, value: b})
    }

    qa.prototype.toString = function () {
        return this.f
    };
    var pa = function () {
        function a(c) {
            if (this instanceof a) throw new TypeError("Symbol is not a constructor");
            return new qa("jscomp_symbol_" + (c || "") + "_" + b++, c)
        }

        var b = 0;
        return a
    }();

    function ra() {
        oa();
        var a = la.Symbol.iterator;
        a || (a = la.Symbol.iterator = la.Symbol("Symbol.iterator"));
        "function" != typeof Array.prototype[a] && ja(Array.prototype, a, {
            configurable: !0,
            writable: !0,
            value: function () {
                return sa(aa(this))
            }
        });
        ra = function () {
        }
    }

    function sa(a) {
        ra();
        a = {next: a};
        a[la.Symbol.iterator] = function () {
            return this
        };
        return a
    }

    function w(a, b) {
        return Object.prototype.hasOwnProperty.call(a, b)
    }

    var ta = "function" == typeof Object.assign ? Object.assign : function (a, b) {
        for (var c = 1; c < arguments.length; c++) {
            var d = arguments[c];
            if (d) for (var e in d) w(d, e) && (a[e] = d[e])
        }
        return a
    };
    u("Object.assign", function (a) {
        return a || ta
    });
    u("WeakMap", function (a) {
        function b(k) {
            this.f = (h += Math.random() + 1).toString();
            if (k) {
                k = q(k);
                for (var l; !(l = k.next()).done;) l = l.value, this.set(l[0], l[1])
            }
        }

        function c() {
        }

        function d(k) {
            var l = typeof k;
            return "object" === l && null !== k || "function" === l
        }

        function e(k) {
            if (!w(k, g)) {
                var l = new c;
                ja(k, g, {value: l})
            }
        }

        function f(k) {
            var l = Object[k];
            l && (Object[k] = function (m) {
                if (m instanceof c) return m;
                e(m);
                return l(m)
            })
        }

        if (function () {
            if (!a || !Object.seal) return !1;
            try {
                var k = Object.seal({}), l = Object.seal({}), m = new a([[k, 2], [l, 3]]);
                if (2 != m.get(k) || 3 != m.get(l)) return !1;
                m["delete"](k);
                m.set(l, 4);
                return !m.has(k) && 4 == m.get(l)
            } catch (p) {
                return !1
            }
        }()) return a;
        var g = "$jscomp_hidden_" + Math.random();
        f("freeze");
        f("preventExtensions");
        f("seal");
        var h = 0;
        b.prototype.set = function (k, l) {
            if (!d(k)) throw Error("Invalid WeakMap key");
            e(k);
            if (!w(k, g)) throw Error("WeakMap key fail: " + k);
            k[g][this.f] = l;
            return this
        };
        b.prototype.get = function (k) {
            return d(k) && w(k, g) ? k[g][this.f] : void 0
        };
        b.prototype.has = function (k) {
            return d(k) && w(k, g) && w(k[g], this.f)
        };
        b.prototype["delete"] = function (k) {
            return d(k) && w(k, g) && w(k[g], this.f) ? delete k[g][this.f] : !1
        };
        return b
    });
    u("Map", function (a) {
        function b() {
            var h = {};
            return h.previous = h.next = h.head = h
        }

        function c(h, k) {
            var l = h.f;
            return sa(function () {
                if (l) {
                    for (; l.head != h.f;) l = l.previous;
                    for (; l.next != l.head;) return l = l.next, {done: !1, value: k(l)};
                    l = null
                }
                return {done: !0, value: void 0}
            })
        }

        function d(h, k) {
            var l = k && typeof k;
            "object" == l || "function" == l ? f.has(k) ? l = f.get(k) : (l = "" + ++g, f.set(k, l)) : l = "p_" + k;
            var m = h.g[l];
            if (m && w(h.g, l)) for (var p = 0; p < m.length; p++) {
                var v = m[p];
                if (k !== k && v.key !== v.key || k === v.key) return {id: l, list: m, index: p, u: v}
            }
            return {id: l, list: m, index: -1, u: void 0}
        }

        function e(h) {
            this.g = {};
            this.f = b();
            this.size = 0;
            if (h) {
                h = q(h);
                for (var k; !(k = h.next()).done;) k = k.value, this.set(k[0], k[1])
            }
        }

        if (function () {
            if (!a || "function" != typeof a || !a.prototype.entries || "function" != typeof Object.seal) return !1;
            try {
                var h = Object.seal({x: 4}), k = new a(q([[h, "s"]]));
                if ("s" != k.get(h) || 1 != k.size || k.get({x: 4}) || k.set({x: 4}, "t") != k || 2 != k.size) return !1;
                var l = k.entries(), m = l.next();
                if (m.done || m.value[0] != h || "s" != m.value[1]) return !1;
                m = l.next();
                return m.done || 4 != m.value[0].x || "t" != m.value[1] || !l.next().done ? !1 : !0
            } catch (p) {
                return !1
            }
        }()) return a;
        ra();
        var f = new WeakMap;
        e.prototype.set = function (h, k) {
            h = 0 === h ? 0 : h;
            var l = d(this, h);
            l.list || (l.list = this.g[l.id] = []);
            l.u ? l.u.value = k : (l.u = {
                next: this.f,
                previous: this.f.previous,
                head: this.f,
                key: h,
                value: k
            }, l.list.push(l.u), this.f.previous.next = l.u, this.f.previous = l.u, this.size++);
            return this
        };
        e.prototype["delete"] = function (h) {
            h = d(this, h);
            return h.u && h.list ? (h.list.splice(h.index, 1), h.list.length || delete this.g[h.id], h.u.previous.next = h.u.next, h.u.next.previous = h.u.previous, h.u.head = null, this.size--, !0) : !1
        };
        e.prototype.clear = function () {
            this.g = {};
            this.f = this.f.previous = b();
            this.size = 0
        };
        e.prototype.has = function (h) {
            return !!d(this, h).u
        };
        e.prototype.get = function (h) {
            return (h = d(this, h).u) && h.value
        };
        e.prototype.entries = function () {
            return c(this, function (h) {
                return [h.key, h.value]
            })
        };
        e.prototype.keys = function () {
            return c(this, function (h) {
                return h.key
            })
        };
        e.prototype.values = function () {
            return c(this, function (h) {
                return h.value
            })
        };
        e.prototype.forEach = function (h, k) {
            for (var l = this.entries(), m; !(m = l.next()).done;) m = m.value, h.call(k, m[1], m[0], this)
        };
        e.prototype[Symbol.iterator] = e.prototype.entries;
        var g = 0;
        return e
    });
    u("Set", function (a) {
        function b(c) {
            this.f = new Map;
            if (c) {
                c = q(c);
                for (var d; !(d = c.next()).done;) this.add(d.value)
            }
            this.size = this.f.size
        }

        if (function () {
            if (!a || "function" != typeof a || !a.prototype.entries || "function" != typeof Object.seal) return !1;
            try {
                var c = Object.seal({x: 4}), d = new a(q([c]));
                if (!d.has(c) || 1 != d.size || d.add(c) != d || 1 != d.size || d.add({x: 4}) != d || 2 != d.size) return !1;
                var e = d.entries(), f = e.next();
                if (f.done || f.value[0] != c || f.value[1] != c) return !1;
                f = e.next();
                return f.done || f.value[0] == c || 4 != f.value[0].x || f.value[1] != f.value[0] ? !1 : e.next().done
            } catch (g) {
                return !1
            }
        }()) return a;
        ra();
        b.prototype.add = function (c) {
            c = 0 === c ? 0 : c;
            this.f.set(c, c);
            this.size = this.f.size;
            return this
        };
        b.prototype["delete"] = function (c) {
            c = this.f["delete"](c);
            this.size = this.f.size;
            return c
        };
        b.prototype.clear = function () {
            this.f.clear();
            this.size = 0
        };
        b.prototype.has = function (c) {
            return this.f.has(c)
        };
        b.prototype.entries = function () {
            return this.f.entries()
        };
        b.prototype.values = function () {
            return this.f.values()
        };
        b.prototype.keys = b.prototype.values;
        b.prototype[Symbol.iterator] = b.prototype.values;
        b.prototype.forEach = function (c, d) {
            var e = this;
            this.f.forEach(function (f) {
                return c.call(d, f, f, e)
            })
        };
        return b
    });
    u("Promise", function (a) {
        function b(g) {
            this.g = 0;
            this.h = void 0;
            this.f = [];
            var h = this.i();
            try {
                g(h.resolve, h.reject)
            } catch (k) {
                h.reject(k)
            }
        }

        function c() {
            this.f = null
        }

        function d(g) {
            return g instanceof b ? g : new b(function (h) {
                h(g)
            })
        }

        if (a) return a;
        c.prototype.g = function (g) {
            if (null == this.f) {
                this.f = [];
                var h = this;
                this.h(function () {
                    h.j()
                })
            }
            this.f.push(g)
        };
        var e = la.setTimeout;
        c.prototype.h = function (g) {
            e(g, 0)
        };
        c.prototype.j = function () {
            for (; this.f && this.f.length;) {
                var g = this.f;
                this.f = [];
                for (var h = 0; h < g.length; ++h) {
                    var k = g[h];
                    g[h] = null;
                    try {
                        k()
                    } catch (l) {
                        this.i(l)
                    }
                }
            }
            this.f = null
        };
        c.prototype.i = function (g) {
            this.h(function () {
                throw g;
            })
        };
        b.prototype.i = function () {
            function g(l) {
                return function (m) {
                    k || (k = !0, l.call(h, m))
                }
            }

            var h = this, k = !1;
            return {resolve: g(this.A), reject: g(this.j)}
        };
        b.prototype.A = function (g) {
            if (g === this) this.j(new TypeError("A Promise cannot resolve to itself")); else if (g instanceof b) this.D(g); else {
                a:switch (typeof g) {
                    case "object":
                        var h = null != g;
                        break;
                    case "function":
                        h = !0;
                        break;
                    default:
                        h = !1
                }
                h ? this.w(g) : this.l(g)
            }
        };
        b.prototype.w = function (g) {
            var h = void 0;
            try {
                h = g.then
            } catch (k) {
                this.j(k);
                return
            }
            "function" == typeof h ? this.F(h, g) : this.l(g)
        };
        b.prototype.j = function (g) {
            this.m(2, g)
        };
        b.prototype.l = function (g) {
            this.m(1, g)
        };
        b.prototype.m = function (g, h) {
            if (0 != this.g) throw Error("Cannot settle(" + g + ", " + h + "): Promise already settled in state" + this.g);
            this.g = g;
            this.h = h;
            this.B()
        };
        b.prototype.B = function () {
            if (null != this.f) {
                for (var g = 0; g < this.f.length; ++g) f.g(this.f[g]);
                this.f = null
            }
        };
        var f = new c;
        b.prototype.D = function (g) {
            var h = this.i();
            g.aa(h.resolve, h.reject)
        };
        b.prototype.F = function (g, h) {
            var k = this.i();
            try {
                g.call(h, k.resolve, k.reject)
            } catch (l) {
                k.reject(l)
            }
        };
        b.prototype.then = function (g, h) {
            function k(v, r) {
                return "function" == typeof v ? function (B) {
                    try {
                        l(v(B))
                    } catch (K) {
                        m(K)
                    }
                } : r
            }

            var l, m, p = new b(function (v, r) {
                l = v;
                m = r
            });
            this.aa(k(g, l), k(h, m));
            return p
        };
        b.prototype["catch"] = function (g) {
            return this.then(void 0, g)
        };
        b.prototype.aa = function (g, h) {
            function k() {
                switch (l.g) {
                    case 1:
                        g(l.h);
                        break;
                    case 2:
                        h(l.h);
                        break;
                    default:
                        throw Error("Unexpected state: " + l.g);
                }
            }

            var l = this;
            null == this.f ? f.g(k) : this.f.push(k)
        };
        b.resolve = d;
        b.reject = function (g) {
            return new b(function (h, k) {
                k(g)
            })
        };
        b.race = function (g) {
            return new b(function (h, k) {
                for (var l = q(g), m = l.next(); !m.done; m = l.next()) d(m.value).aa(h, k)
            })
        };
        b.all = function (g) {
            var h = q(g), k = h.next();
            return k.done ? d([]) : new b(function (l, m) {
                function p(B) {
                    return function (K) {
                        v[B] = K;
                        r--;
                        0 == r && l(v)
                    }
                }

                var v = [], r = 0;
                do v.push(void 0), r++, d(k.value).aa(p(v.length - 1), m), k = h.next(); while (!k.done)
            })
        };
        return b
    });
    u("String.prototype.includes", function (a) {
        return a ? a : function (b, c) {
            return -1 !== na(this, b, "includes").indexOf(b, c || 0)
        }
    });
    var ua = function () {
        function a() {
            function c() {
            }

            new c;
            Reflect.construct(c, [], function () {
            });
            return new c instanceof c
        }

        if ("undefined" != typeof Reflect && Reflect.construct) {
            if (a()) return Reflect.construct;
            var b = Reflect.construct;
            return function (c, d, e) {
                c = b(c, d);
                e && Reflect.setPrototypeOf(c, e.prototype);
                return c
            }
        }
        return function (c, d, e) {
            void 0 === e && (e = c);
            e = ca(e.prototype || Object.prototype);
            return Function.prototype.apply.call(c, e, d) || e
        }
    }();
    u("Reflect.construct", function () {
        return ua
    });
    var x = this || self;

    function y(a, b, c) {
        a = a.split(".");
        c = c || x;
        a[0] in c || "undefined" == typeof c.execScript || c.execScript("var " + a[0]);
        for (var d; a.length && (d = a.shift());) a.length || void 0 === b ? c[d] && c[d] !== Object.prototype[d] ? c = c[d] : c = c[d] = {} : c[d] = b
    }

    var va = /^[\w+/_-]+[=]{0,2}$/, wa = null;

    function z(a, b) {
        for (var c = a.split("."), d = b || x, e = 0; e < c.length; e++) if (d = d[c[e]], null == d) return null;
        return d
    }

    function xa() {
    }

    function ya(a) {
        a.ia = void 0;
        a.getInstance = function () {
            return a.ia ? a.ia : a.ia = new a
        }
    }

    function za(a) {
        var b = typeof a;
        if ("object" == b) if (a) {
            if (a instanceof Array) return "array";
            if (a instanceof Object) return b;
            var c = Object.prototype.toString.call(a);
            if ("[object Window]" == c) return "object";
            if ("[object Array]" == c || "number" == typeof a.length && "undefined" != typeof a.splice && "undefined" != typeof a.propertyIsEnumerable && !a.propertyIsEnumerable("splice")) return "array";
            if ("[object Function]" == c || "undefined" != typeof a.call && "undefined" != typeof a.propertyIsEnumerable && !a.propertyIsEnumerable("call")) return "function"
        } else return "null";
        else if ("function" == b && "undefined" == typeof a.call) return "object";
        return b
    }

    function Aa(a) {
        return "array" == za(a)
    }

    function Ba(a) {
        var b = za(a);
        return "array" == b || "object" == b && "number" == typeof a.length
    }

    function A(a) {
        return "function" == za(a)
    }

    function Ca(a) {
        var b = typeof a;
        return "object" == b && null != a || "function" == b
    }

    function Da(a) {
        return Object.prototype.hasOwnProperty.call(a, Ea) && a[Ea] || (a[Ea] = ++Fa)
    }

    var Ea = "closure_uid_" + (1E9 * Math.random() >>> 0), Fa = 0;

    function Ga(a, b, c) {
        return a.call.apply(a.bind, arguments)
    }

    function Ha(a, b, c) {
        if (!a) throw Error();
        if (2 < arguments.length) {
            var d = Array.prototype.slice.call(arguments, 2);
            return function () {
                var e = Array.prototype.slice.call(arguments);
                Array.prototype.unshift.apply(e, d);
                return a.apply(b, e)
            }
        }
        return function () {
            return a.apply(b, arguments)
        }
    }

    function C(a, b, c) {
        Function.prototype.bind && -1 != Function.prototype.bind.toString().indexOf("native code") ? C = Ga : C = Ha;
        return C.apply(null, arguments)
    }

    function Ja(a, b) {
        var c = Array.prototype.slice.call(arguments, 1);
        return function () {
            var d = c.slice();
            d.push.apply(d, arguments);
            return a.apply(this, d)
        }
    }

    var D = Date.now || function () {
        return +new Date
    };

    function Ka(a, b) {
        y(a, b, void 0)
    }

    function E(a, b) {
        function c() {
        }

        c.prototype = b.prototype;
        a.L = b.prototype;
        a.prototype = new c;
        a.prototype.constructor = a
    }

    function F(a) {
        if (Error.captureStackTrace) Error.captureStackTrace(this, F); else {
            var b = Error().stack;
            b && (this.stack = b)
        }
        a && (this.message = String(a))
    }

    E(F, Error);
    F.prototype.name = "CustomError";
    var La = Array.prototype.indexOf ? function (a, b) {
        return Array.prototype.indexOf.call(a, b, void 0)
    } : function (a, b) {
        if ("string" === typeof a) return "string" !== typeof b || 1 != b.length ? -1 : a.indexOf(b, 0);
        for (var c = 0; c < a.length; c++) if (c in a && a[c] === b) return c;
        return -1
    }, G = Array.prototype.forEach ? function (a, b, c) {
        Array.prototype.forEach.call(a, b, c)
    } : function (a, b, c) {
        for (var d = a.length, e = "string" === typeof a ? a.split("") : a, f = 0; f < d; f++) f in e && b.call(c, e[f], f, a)
    }, Ma = Array.prototype.filter ? function (a, b) {
        return Array.prototype.filter.call(a, b, void 0)
    } : function (a, b) {
        for (var c = a.length, d = [], e = 0, f = "string" === typeof a ? a.split("") : a, g = 0; g < c; g++) if (g in f) {
            var h = f[g];
            b.call(void 0, h, g, a) && (d[e++] = h)
        }
        return d
    }, Na = Array.prototype.map ? function (a, b) {
        return Array.prototype.map.call(a, b, void 0)
    } : function (a, b) {
        for (var c = a.length, d = Array(c), e = "string" === typeof a ? a.split("") : a, f = 0; f < c; f++) f in e && (d[f] = b.call(void 0, e[f], f, a));
        return d
    }, Oa = Array.prototype.reduce ? function (a, b, c) {
        return Array.prototype.reduce.call(a, b, c)
    } : function (a, b, c) {
        var d = c;
        G(a, function (e, f) {
            d = b.call(void 0, d, e, f, a)
        });
        return d
    };

    function Pa(a, b) {
        a:{
            var c = a.length;
            for (var d = "string" === typeof a ? a.split("") : a, e = 0; e < c; e++) if (e in d && b.call(void 0, d[e], e, a)) {
                c = e;
                break a
            }
            c = -1
        }
        return 0 > c ? null : "string" === typeof a ? a.charAt(c) : a[c]
    }

    function Qa(a, b) {
        var c = La(a, b);
        0 <= c && Array.prototype.splice.call(a, c, 1)
    }

    function Ra(a) {
        var b = a.length;
        if (0 < b) {
            for (var c = Array(b), d = 0; d < b; d++) c[d] = a[d];
            return c
        }
        return []
    }

    function Sa(a, b) {
        for (var c = 1; c < arguments.length; c++) {
            var d = arguments[c];
            if (Ba(d)) {
                var e = a.length || 0, f = d.length || 0;
                a.length = e + f;
                for (var g = 0; g < f; g++) a[e + g] = d[g]
            } else a.push(d)
        }
    }

    var Ta = String.prototype.trim ? function (a) {
        return a.trim()
    } : function (a) {
        return /^[\s\xa0]*([\s\S]*?)[\s\xa0]*$/.exec(a)[1]
    };

    function Ua(a, b) {
        if (b) a = a.replace(Va, "&amp;").replace(Wa, "&lt;").replace(Xa, "&gt;").replace(Ya, "&quot;").replace(Za, "&#39;").replace($a, "&#0;"); else {
            if (!ab.test(a)) return a;
            -1 != a.indexOf("&") && (a = a.replace(Va, "&amp;"));
            -1 != a.indexOf("<") && (a = a.replace(Wa, "&lt;"));
            -1 != a.indexOf(">") && (a = a.replace(Xa, "&gt;"));
            -1 != a.indexOf('"') && (a = a.replace(Ya, "&quot;"));
            -1 != a.indexOf("'") && (a = a.replace(Za, "&#39;"));
            -1 != a.indexOf("\x00") && (a = a.replace($a, "&#0;"))
        }
        return a
    }

    var Va = /&/g, Wa = /</g, Xa = />/g, Ya = /"/g, Za = /'/g, $a = /\x00/g, ab = /[\x00&<>"']/;
    var bb;
    a:{
        var cb = x.navigator;
        if (cb) {
            var db = cb.userAgent;
            if (db) {
                bb = db;
                break a
            }
        }
        bb = ""
    }

    function eb(a, b) {
        for (var c in a) b.call(void 0, a[c], c, a)
    }

    function fb(a, b) {
        var c = Ba(b), d = c ? b : arguments;
        for (c = c ? 0 : 1; c < d.length; c++) {
            if (null == a) return;
            a = a[d[c]]
        }
        return a
    }

    function gb(a) {
        var b = hb, c;
        for (c in b) if (a.call(void 0, b[c], c, b)) return c
    }

    function ib(a) {
        for (var b in a) return !1;
        return !0
    }

    function jb(a, b) {
        if (null !== a && b in a) throw Error('The object already contains the key "' + b + '"');
        a[b] = !0
    }

    function kb(a, b) {
        for (var c in a) if (!(c in b) || a[c] !== b[c]) return !1;
        for (var d in b) if (!(d in a)) return !1;
        return !0
    }

    function lb(a) {
        var b = {}, c;
        for (c in a) b[c] = a[c];
        return b
    }

    function mb(a) {
        var b = za(a);
        if ("object" == b || "array" == b) {
            if (A(a.clone)) return a.clone();
            b = "array" == b ? [] : {};
            for (var c in a) b[c] = mb(a[c]);
            return b
        }
        return a
    }

    var nb = "constructor hasOwnProperty isPrototypeOf propertyIsEnumerable toLocaleString toString valueOf".split(" ");

    function ob(a, b) {
        for (var c, d, e = 1; e < arguments.length; e++) {
            d = arguments[e];
            for (c in d) a[c] = d[c];
            for (var f = 0; f < nb.length; f++) c = nb[f], Object.prototype.hasOwnProperty.call(d, c) && (a[c] = d[c])
        }
    }

    function pb(a) {
        var b = !1, c;
        return function () {
            b || (c = a(), b = !0);
            return c
        }
    }

    function qb(a, b) {
        this.f = a === rb && b || "";
        this.g = sb
    }

    qb.prototype.J = !0;
    qb.prototype.I = function () {
        return this.f
    };

    function tb() {
        var a = ub;
        return a instanceof qb && a.constructor === qb && a.g === sb ? a.f : "type_error:Const"
    }

    var sb = {}, rb = {}, ub = new qb(rb, "");

    function vb(a, b) {
        this.f = a === wb && b || "";
        this.g = xb
    }

    vb.prototype.J = !0;
    vb.prototype.I = function () {
        return this.f.toString()
    };
    vb.prototype.ha = !0;
    vb.prototype.ea = function () {
        return 1
    };

    function yb(a) {
        if (a instanceof vb && a.constructor === vb && a.g === xb) return a.f;
        za(a);
        return "type_error:TrustedResourceUrl"
    }

    var xb = {}, wb = {};

    function H(a, b) {
        this.f = a === zb && b || "";
        this.g = Ab
    }

    H.prototype.J = !0;
    H.prototype.I = function () {
        return this.f.toString()
    };
    H.prototype.ha = !0;
    H.prototype.ea = function () {
        return 1
    };

    function Bb(a) {
        if (a instanceof H && a.constructor === H && a.g === Ab) return a.f;
        za(a);
        return "type_error:SafeUrl"
    }

    var Cb = /^(?:(?:https?|mailto|ftp):|[^:/?#]*(?:[/?#]|$))/i;

    function Db(a) {
        if (a instanceof H) return a;
        a = "object" == typeof a && a.J ? a.I() : String(a);
        Cb.test(a) || (a = "about:invalid#zClosurez");
        return new H(zb, a)
    }

    var Ab = {}, zb = {};

    function Eb() {
        this.f = "";
        this.h = Gb;
        this.g = null
    }

    Eb.prototype.ha = !0;
    Eb.prototype.ea = function () {
        return this.g
    };
    Eb.prototype.J = !0;
    Eb.prototype.I = function () {
        return this.f.toString()
    };

    function Hb(a) {
        if (a instanceof Eb && a.constructor === Eb && a.h === Gb) return a.f;
        za(a);
        return "type_error:SafeHtml"
    }

    var Gb = {};

    function Ib(a, b) {
        var c = new Eb;
        c.f = a;
        c.g = b;
        return c
    }

    Ib("<!DOCTYPE html>", 0);
    var Jb = Ib("", 0);
    Ib("<br>", 0);

    function Kb(a, b) {
        var c = b instanceof H ? b : Db(b);
        a.href = Bb(c)
    }

    function Lb(a, b) {
        a.src = yb(b);
        if (null === wa) b:{
            var c = x.document;
            if ((c = c.querySelector && c.querySelector("script[nonce]")) && (c = c.nonce || c.getAttribute("nonce")) && va.test(c)) {
                wa = c;
                break b
            }
            wa = ""
        }
        c = wa;
        c && a.setAttribute("nonce", c)
    }

    function Mb(a) {
        return a = Ua(a, void 0)
    }

    function Nb(a) {
        for (var b = 0, c = 0; c < a.length; ++c) b = 31 * b + a.charCodeAt(c) >>> 0;
        return b
    }

    var Ob = -1 != bb.indexOf("Trident") || -1 != bb.indexOf("MSIE"), Pb;
    var Qb;
    if (x.document && Ob) {
        var Rb = x.document;
        Qb = Rb ? Rb.documentMode : void 0
    } else Qb = void 0;
    Pb = Qb;
    var Sb = {}, Tb = null;
    var I = window;

    function Ub(a) {
        this.f = a || {cookie: ""}
    }

    n = Ub.prototype;
    n.isEnabled = function () {
        return navigator.cookieEnabled
    };
    n.set = function (a, b, c) {
        var d = !1;
        if ("object" === typeof c) {
            var e = c.ub;
            d = c.secure || !1;
            var f = c.domain || void 0;
            var g = c.path || void 0;
            var h = c.ma
        }
        if (/[;=\s]/.test(a)) throw Error('Invalid cookie name "' + a + '"');
        if (/[;\r\n]/.test(b)) throw Error('Invalid cookie value "' + b + '"');
        void 0 === h && (h = -1);
        c = f ? ";domain=" + f : "";
        g = g ? ";path=" + g : "";
        d = d ? ";secure" : "";
        h = 0 > h ? "" : 0 == h ? ";expires=" + (new Date(1970, 1, 1)).toUTCString() : ";expires=" + (new Date(D() + 1E3 * h)).toUTCString();
        this.f.cookie = a + "=" + b + c + g + h + d + (null != e ? ";samesite=" + e :
            "")
    };
    n.get = function (a, b) {
        for (var c = a + "=", d = (this.f.cookie || "").split(";"), e = 0, f; e < d.length; e++) {
            f = Ta(d[e]);
            if (0 == f.lastIndexOf(c, 0)) return f.substr(c.length);
            if (f == a) return ""
        }
        return b
    };
    n.remove = function (a, b, c) {
        var d = void 0 !== this.get(a);
        this.set(a, "", {ma: 0, path: b, domain: c});
        return d
    };
    n.isEmpty = function () {
        return !this.f.cookie
    };
    n.clear = function () {
        for (var a = (this.f.cookie || "").split(";"), b = [], c = [], d, e, f = 0; f < a.length; f++) e = Ta(a[f]), d = e.indexOf("="), -1 == d ? (b.push(""), c.push(e)) : (b.push(e.substring(0, d)), c.push(e.substring(d + 1)));
        for (a = b.length - 1; 0 <= a; a--) this.remove(b[a])
    };
    var Vb = new Ub("undefined" == typeof document ? null : document);

    function Wb(a) {
        var b = z("window.location.href");
        null == a && (a = 'Unknown Error of type "null/undefined"');
        if ("string" === typeof a) return {
            message: a,
            name: "Unknown error",
            lineNumber: "Not available",
            fileName: b,
            stack: "Not available"
        };
        var c = !1;
        try {
            var d = a.lineNumber || a.line || "Not available"
        } catch (f) {
            d = "Not available", c = !0
        }
        try {
            var e = a.fileName || a.filename || a.sourceURL || x.$googDebugFname || b
        } catch (f) {
            e = "Not available", c = !0
        }
        return !c && a.lineNumber && a.fileName && a.stack && a.message && a.name ? a : (b = a.message, null == b && (a.constructor &&
        a.constructor instanceof Function ? (a.constructor.name ? b = a.constructor.name : (b = a.constructor, Xb[b] ? b = Xb[b] : (b = String(b), Xb[b] || (c = /function\s+([^\(]+)/m.exec(b), Xb[b] = c ? c[1] : "[Anonymous]"), b = Xb[b])), b = 'Unknown Error of type "' + b + '"') : b = "Unknown Error of unknown type"), {
            message: b,
            name: a.name || "UnknownError",
            lineNumber: d,
            fileName: e,
            stack: a.stack || "Not available"
        })
    }

    var Xb = {};
    var Yb = !Ob || 9 <= Number(Pb);

    function Zb(a, b) {
        this.x = void 0 !== a ? a : 0;
        this.y = void 0 !== b ? b : 0
    }

    n = Zb.prototype;
    n.clone = function () {
        return new Zb(this.x, this.y)
    };
    n.equals = function (a) {
        return a instanceof Zb && (this == a ? !0 : this && a ? this.x == a.x && this.y == a.y : !1)
    };
    n.ceil = function () {
        this.x = Math.ceil(this.x);
        this.y = Math.ceil(this.y);
        return this
    };
    n.floor = function () {
        this.x = Math.floor(this.x);
        this.y = Math.floor(this.y);
        return this
    };
    n.round = function () {
        this.x = Math.round(this.x);
        this.y = Math.round(this.y);
        return this
    };

    function $b(a, b) {
        this.width = a;
        this.height = b
    }

    n = $b.prototype;
    n.clone = function () {
        return new $b(this.width, this.height)
    };
    n.aspectRatio = function () {
        return this.width / this.height
    };
    n.isEmpty = function () {
        return !(this.width * this.height)
    };
    n.ceil = function () {
        this.width = Math.ceil(this.width);
        this.height = Math.ceil(this.height);
        return this
    };
    n.floor = function () {
        this.width = Math.floor(this.width);
        this.height = Math.floor(this.height);
        return this
    };
    n.round = function () {
        this.width = Math.round(this.width);
        this.height = Math.round(this.height);
        return this
    };

    function ac(a) {
        var b = document;
        return "string" === typeof a ? b.getElementById(a) : a
    }

    function bc(a, b) {
        eb(b, function (c, d) {
            c && "object" == typeof c && c.J && (c = c.I());
            "style" == d ? a.style.cssText = c : "class" == d ? a.className = c : "for" == d ? a.htmlFor = c : cc.hasOwnProperty(d) ? a.setAttribute(cc[d], c) : 0 == d.lastIndexOf("aria-", 0) || 0 == d.lastIndexOf("data-", 0) ? a.setAttribute(d, c) : a[d] = c
        })
    }

    var cc = {
        cellpadding: "cellPadding",
        cellspacing: "cellSpacing",
        colspan: "colSpan",
        frameborder: "frameBorder",
        height: "height",
        maxlength: "maxLength",
        nonce: "nonce",
        role: "role",
        rowspan: "rowSpan",
        type: "type",
        usemap: "useMap",
        valign: "vAlign",
        width: "width"
    };

    function dc(a, b, c) {
        var d = arguments, e = document, f = String(d[0]), g = d[1];
        if (!Yb && g && (g.name || g.type)) {
            f = ["<", f];
            g.name && f.push(' name="', Mb(g.name), '"');
            if (g.type) {
                f.push(' type="', Mb(g.type), '"');
                var h = {};
                ob(h, g);
                delete h.type;
                g = h
            }
            f.push(">");
            f = f.join("")
        }
        f = ec(e, f);
        g && ("string" === typeof g ? f.className = g : Aa(g) ? f.className = g.join(" ") : bc(f, g));
        2 < d.length && fc(e, f, d);
        return f
    }

    function fc(a, b, c) {
        function d(g) {
            g && b.appendChild("string" === typeof g ? a.createTextNode(g) : g)
        }

        for (var e = 2; e < c.length; e++) {
            var f = c[e];
            !Ba(f) || Ca(f) && 0 < f.nodeType ? d(f) : G(gc(f) ? Ra(f) : f, d)
        }
    }

    function ec(a, b) {
        b = String(b);
        "application/xhtml+xml" === a.contentType && (b = b.toLowerCase());
        return a.createElement(b)
    }

    function gc(a) {
        if (a && "number" == typeof a.length) {
            if (Ca(a)) return "function" == typeof a.item || "string" == typeof a.item;
            if (A(a)) return "function" == typeof a.item
        }
        return !1
    }

    function hc(a, b) {
        for (var c = 0; a;) {
            if (b(a)) return a;
            a = a.parentNode;
            c++
        }
        return null
    }

    function ic(a) {
        jc();
        return new vb(wb, a)
    }

    var jc = xa;
    var kc = /^(?:([^:/?#.]+):)?(?:\/\/(?:([^/?#]*)@)?([^/#?]*?)(?::([0-9]+))?(?=[/\\#?]|$))?([^?#]+)?(?:\?([^#]*))?(?:#([\s\S]*))?$/;

    function J(a) {
        return a ? decodeURI(a) : a
    }

    function L(a, b) {
        return b.match(kc)[a] || null
    }

    function lc(a, b, c) {
        if (Aa(b)) for (var d = 0; d < b.length; d++) lc(a, String(b[d]), c); else null != b && c.push(a + ("" === b ? "" : "=" + encodeURIComponent(String(b))))
    }

    function mc(a) {
        var b = [], c;
        for (c in a) lc(c, a[c], b);
        return b.join("&")
    }

    function nc(a, b) {
        var c = mc(b);
        if (c) {
            var d = a.indexOf("#");
            0 > d && (d = a.length);
            var e = a.indexOf("?");
            if (0 > e || e > d) {
                e = d;
                var f = ""
            } else f = a.substring(e + 1, d);
            d = [a.substr(0, e), f, a.substr(d)];
            e = d[1];
            d[1] = c ? e ? e + "&" + c : c : e;
            c = d[0] + (d[1] ? "?" + d[1] : "") + d[2]
        } else c = a;
        return c
    }

    var oc = /#|$/;

    function pc(a, b) {
        var c = a.search(oc);
        a:{
            var d = 0;
            for (var e = b.length; 0 <= (d = a.indexOf(b, d)) && d < c;) {
                var f = a.charCodeAt(d - 1);
                if (38 == f || 63 == f) if (f = a.charCodeAt(d + e), !f || 61 == f || 38 == f || 35 == f) break a;
                d += e + 1
            }
            d = -1
        }
        if (0 > d) return null;
        e = a.indexOf("&", d);
        if (0 > e || e > c) e = c;
        d += b.length + 1;
        return decodeURIComponent(a.substr(d, e - d).replace(/\+/g, " "))
    }

    function qc(a) {
        var b = rc;
        if (b) for (var c in b) Object.prototype.hasOwnProperty.call(b, c) && a.call(void 0, b[c], c, b)
    }

    function sc() {
        var a = [];
        qc(function (b) {
            a.push(b)
        });
        return a
    }

    var rc = {
        ab: "allow-forms",
        bb: "allow-modals",
        cb: "allow-orientation-lock",
        eb: "allow-pointer-lock",
        fb: "allow-popups",
        gb: "allow-popups-to-escape-sandbox",
        hb: "allow-presentation",
        ib: "allow-same-origin",
        jb: "allow-scripts",
        kb: "allow-top-navigation",
        lb: "allow-top-navigation-by-user-activation"
    }, tc = pb(function () {
        return sc()
    });

    function uc() {
        var a = ec(document, "IFRAME"), b = {};
        G(tc(), function (c) {
            a.sandbox && a.sandbox.supports && a.sandbox.supports(c) && (b[c] = !0)
        });
        return b
    }

    function vc(a) {
        "number" == typeof a && (a = Math.round(a) + "px");
        return a
    }

    var wc = (new Date).getTime();

    function xc(a) {
        if (!a) return "";
        a = a.split("#")[0].split("?")[0];
        a = a.toLowerCase();
        0 == a.indexOf("//") && (a = window.location.protocol + a);
        /^[\w\-]*:\/\//.test(a) || (a = window.location.href);
        var b = a.substring(a.indexOf("://") + 3), c = b.indexOf("/");
        -1 != c && (b = b.substring(0, c));
        a = a.substring(0, a.indexOf("://"));
        if ("http" !== a && "https" !== a && "chrome-extension" !== a && "file" !== a && "android-app" !== a && "chrome-search" !== a && "app" !== a) throw Error("Invalid URI scheme in origin: " + a);
        c = "";
        var d = b.indexOf(":");
        if (-1 != d) {
            var e =
                b.substring(d + 1);
            b = b.substring(0, d);
            if ("http" === a && "80" !== e || "https" === a && "443" !== e) c = ":" + e
        }
        return a + "://" + b + c
    }

    function yc() {
        function a() {
            e[0] = 1732584193;
            e[1] = 4023233417;
            e[2] = 2562383102;
            e[3] = 271733878;
            e[4] = 3285377520;
            m = l = 0
        }

        function b(p) {
            for (var v = g, r = 0; 64 > r; r += 4) v[r / 4] = p[r] << 24 | p[r + 1] << 16 | p[r + 2] << 8 | p[r + 3];
            for (r = 16; 80 > r; r++) p = v[r - 3] ^ v[r - 8] ^ v[r - 14] ^ v[r - 16], v[r] = (p << 1 | p >>> 31) & 4294967295;
            p = e[0];
            var B = e[1], K = e[2], ma = e[3], Dc = e[4];
            for (r = 0; 80 > r; r++) {
                if (40 > r) if (20 > r) {
                    var Ia = ma ^ B & (K ^ ma);
                    var Fb = 1518500249
                } else Ia = B ^ K ^ ma, Fb = 1859775393; else 60 > r ? (Ia = B & K | ma & (B | K), Fb = 2400959708) : (Ia = B ^ K ^ ma, Fb = 3395469782);
                Ia = ((p << 5 | p >>> 27) & 4294967295) + Ia + Dc + Fb + v[r] & 4294967295;
                Dc = ma;
                ma = K;
                K = (B << 30 | B >>> 2) & 4294967295;
                B = p;
                p = Ia
            }
            e[0] = e[0] + p & 4294967295;
            e[1] =
                e[1] + B & 4294967295;
            e[2] = e[2] + K & 4294967295;
            e[3] = e[3] + ma & 4294967295;
            e[4] = e[4] + Dc & 4294967295
        }

        function c(p, v) {
            if ("string" === typeof p) {
                p = unescape(encodeURIComponent(p));
                for (var r = [], B = 0, K = p.length; B < K; ++B) r.push(p.charCodeAt(B));
                p = r
            }
            v || (v = p.length);
            r = 0;
            if (0 == l) for (; r + 64 < v;) b(p.slice(r, r + 64)), r += 64, m += 64;
            for (; r < v;) if (f[l++] = p[r++], m++, 64 == l) for (l = 0, b(f); r + 64 < v;) b(p.slice(r, r + 64)), r += 64, m += 64
        }

        function d() {
            var p = [], v = 8 * m;
            56 > l ? c(h, 56 - l) : c(h, 64 - (l - 56));
            for (var r = 63; 56 <= r; r--) f[r] = v & 255, v >>>= 8;
            b(f);
            for (r = v = 0; 5 > r; r++) for (var B = 24; 0 <= B; B -= 8) p[v++] = e[r] >> B & 255;
            return p
        }

        for (var e = [], f = [], g = [], h = [128], k = 1; 64 > k; ++k) h[k] = 0;
        var l, m;
        a();
        return {
            reset: a, update: c, digest: d, xa: function () {
                for (var p = d(), v = "", r = 0; r < p.length; r++) v += "0123456789ABCDEF".charAt(Math.floor(p[r] / 16)) + "0123456789ABCDEF".charAt(p[r] % 16);
                return v
            }
        }
    }

    function zc(a, b, c) {
        var d = [], e = [];
        if (1 == (Aa(c) ? 2 : 1)) return e = [b, a], G(d, function (h) {
            e.push(h)
        }), Ac(e.join(" "));
        var f = [], g = [];
        G(c, function (h) {
            g.push(h.key);
            f.push(h.value)
        });
        c = Math.floor((new Date).getTime() / 1E3);
        e = 0 == f.length ? [c, b, a] : [f.join(":"), c, b, a];
        G(d, function (h) {
            e.push(h)
        });
        a = Ac(e.join(" "));
        a = [c, a];
        0 == g.length || a.push(g.join(""));
        return a.join("_")
    }

    function Ac(a) {
        var b = yc();
        b.update(a);
        return b.xa().toLowerCase()
    }

    function Bc(a) {
        var b = xc(String(x.location.href)), c;
        (c = x.__SAPISID || x.__APISID || x.__OVERRIDE_SID) ? c = !0 : (c = new Ub(document), c = c.get("SAPISID") || c.get("APISID") || c.get("__Secure-3PAPISID") || c.get("SID"), c = !!c);
        if (c && (c = (b = 0 == b.indexOf("https:") || 0 == b.indexOf("chrome-extension:")) ? x.__SAPISID : x.__APISID, c || (c = new Ub(document), c = c.get(b ? "SAPISID" : "APISID") || c.get("__Secure-3PAPISID")), c)) {
            b = b ? "SAPISIDHASH" : "APISIDHASH";
            var d = String(x.location.href);
            return d && c && b ? [b, zc(xc(d), c, a || null)].join(" ") : null
        }
        return null
    }

    function Cc(a) {
        return "string" == typeof a.className ? a.className : a.getAttribute && a.getAttribute("class") || ""
    }

    function Ec(a, b) {
        "string" == typeof a.className ? a.className = b : a.setAttribute && a.setAttribute("class", b)
    }

    function Fc(a, b) {
        if (a.classList) var c = a.classList.contains(b); else c = a.classList ? a.classList : Cc(a).match(/\S+/g) || [], c = 0 <= La(c, b);
        return c
    }

    function Gc() {
        var a = document.body;
        a.classList ? a.classList.remove("inverted-hdpi") : Fc(a, "inverted-hdpi") && Ec(a, Ma(a.classList ? a.classList : Cc(a).match(/\S+/g) || [], function (b) {
            return "inverted-hdpi" != b
        }).join(" "))
    }

    var Hc = "StopIteration" in x ? x.StopIteration : {message: "StopIteration", stack: ""};

    function Ic() {
    }

    Ic.prototype.next = function () {
        throw Hc;
    };
    Ic.prototype.G = function () {
        return this
    };

    function Jc(a) {
        if (a instanceof Ic) return a;
        if ("function" == typeof a.G) return a.G(!1);
        if (Ba(a)) {
            var b = 0, c = new Ic;
            c.next = function () {
                for (; ;) {
                    if (b >= a.length) throw Hc;
                    if (b in a) return a[b++];
                    b++
                }
            };
            return c
        }
        throw Error("Not implemented");
    }

    function Kc(a, b) {
        if (Ba(a)) try {
            G(a, b, void 0)
        } catch (c) {
            if (c !== Hc) throw c;
        } else {
            a = Jc(a);
            try {
                for (; ;) b.call(void 0, a.next(), void 0, a)
            } catch (c) {
                if (c !== Hc) throw c;
            }
        }
    }

    function Lc(a) {
        if (Ba(a)) return Ra(a);
        a = Jc(a);
        var b = [];
        Kc(a, function (c) {
            b.push(c)
        });
        return b
    }

    function Mc(a, b) {
        this.h = {};
        this.f = [];
        this.H = this.g = 0;
        var c = arguments.length;
        if (1 < c) {
            if (c % 2) throw Error("Uneven number of arguments");
            for (var d = 0; d < c; d += 2) this.set(arguments[d], arguments[d + 1])
        } else if (a) if (a instanceof Mc) for (c = Nc(a), d = 0; d < c.length; d++) this.set(c[d], a.get(c[d])); else for (d in a) this.set(d, a[d])
    }

    function Nc(a) {
        Oc(a);
        return a.f.concat()
    }

    n = Mc.prototype;
    n.equals = function (a, b) {
        if (this === a) return !0;
        if (this.g != a.g) return !1;
        var c = b || Pc;
        Oc(this);
        for (var d, e = 0; d = this.f[e]; e++) if (!c(this.get(d), a.get(d))) return !1;
        return !0
    };

    function Pc(a, b) {
        return a === b
    }

    n.isEmpty = function () {
        return 0 == this.g
    };
    n.clear = function () {
        this.h = {};
        this.H = this.g = this.f.length = 0
    };
    n.remove = function (a) {
        return Object.prototype.hasOwnProperty.call(this.h, a) ? (delete this.h[a], this.g--, this.H++, this.f.length > 2 * this.g && Oc(this), !0) : !1
    };

    function Oc(a) {
        if (a.g != a.f.length) {
            for (var b = 0, c = 0; b < a.f.length;) {
                var d = a.f[b];
                Object.prototype.hasOwnProperty.call(a.h, d) && (a.f[c++] = d);
                b++
            }
            a.f.length = c
        }
        if (a.g != a.f.length) {
            var e = {};
            for (c = b = 0; b < a.f.length;) d = a.f[b], Object.prototype.hasOwnProperty.call(e, d) || (a.f[c++] = d, e[d] = 1), b++;
            a.f.length = c
        }
    }

    n.get = function (a, b) {
        return Object.prototype.hasOwnProperty.call(this.h, a) ? this.h[a] : b
    };
    n.set = function (a, b) {
        Object.prototype.hasOwnProperty.call(this.h, a) || (this.g++, this.f.push(a), this.H++);
        this.h[a] = b
    };
    n.forEach = function (a, b) {
        for (var c = Nc(this), d = 0; d < c.length; d++) {
            var e = c[d], f = this.get(e);
            a.call(b, f, e, this)
        }
    };
    n.clone = function () {
        return new Mc(this)
    };
    n.G = function (a) {
        Oc(this);
        var b = 0, c = this.H, d = this, e = new Ic;
        e.next = function () {
            if (c != d.H) throw Error("The map has changed since the iterator was created");
            if (b >= d.f.length) throw Hc;
            var f = d.f[b++];
            return a ? f : d.h[f]
        };
        return e
    };

    function Qc(a) {
        if (!a) return !1;
        try {
            return !!a.$goog_Thenable
        } catch (b) {
            return !1
        }
    }

    function Rc(a, b) {
        this.h = a;
        this.i = b;
        this.g = 0;
        this.f = null
    }

    Rc.prototype.get = function () {
        if (0 < this.g) {
            this.g--;
            var a = this.f;
            this.f = a.next;
            a.next = null
        } else a = this.h();
        return a
    };

    function Sc(a, b) {
        a.i(b);
        100 > a.g && (a.g++, b.next = a.f, a.f = b)
    }

    function Tc(a) {
        x.setTimeout(function () {
            throw a;
        }, 0)
    }

    var Uc;

    function Vc() {
        var a = x.MessageChannel;
        "undefined" === typeof a && "undefined" !== typeof window && window.postMessage && window.addEventListener && -1 == bb.indexOf("Presto") && (a = function () {
            var e = ec(document, "IFRAME");
            e.style.display = "none";
            e.src = yb(new vb(wb, tb())).toString();
            document.documentElement.appendChild(e);
            var f = e.contentWindow;
            e = f.document;
            e.open();
            e.write(Hb(Jb));
            e.close();
            var g = "callImmediate" + Math.random(),
                h = "file:" == f.location.protocol ? "*" : f.location.protocol + "//" + f.location.host;
            e = C(function (k) {
                if (("*" == h ||
                    k.origin == h) && k.data == g) this.port1.onmessage()
            }, this);
            f.addEventListener("message", e, !1);
            this.port1 = {};
            this.port2 = {
                postMessage: function () {
                    f.postMessage(g, h)
                }
            }
        });
        if ("undefined" !== typeof a && -1 == bb.indexOf("Trident") && -1 == bb.indexOf("MSIE")) {
            var b = new a, c = {}, d = c;
            b.port1.onmessage = function () {
                if (void 0 !== c.next) {
                    c = c.next;
                    var e = c.la;
                    c.la = null;
                    e()
                }
            };
            return function (e) {
                d.next = {la: e};
                d = d.next;
                b.port2.postMessage(0)
            }
        }
        return "undefined" !== typeof document && "onreadystatechange" in ec(document, "SCRIPT") ? function (e) {
            var f = ec(document, "SCRIPT");
            f.onreadystatechange = function () {
                f.onreadystatechange = null;
                f.parentNode.removeChild(f);
                f = null;
                e();
                e = null
            };
            document.documentElement.appendChild(f)
        } : function (e) {
            x.setTimeout(e, 0)
        }
    }

    function Wc() {
        this.g = this.f = null
    }

    var Yc = new Rc(function () {
        return new Xc
    }, function (a) {
        a.reset()
    });
    Wc.prototype.add = function (a, b) {
        var c = Yc.get();
        c.set(a, b);
        this.g ? this.g.next = c : this.f = c;
        this.g = c
    };
    Wc.prototype.remove = function () {
        var a = null;
        this.f && (a = this.f, this.f = this.f.next, this.f || (this.g = null), a.next = null);
        return a
    };

    function Xc() {
        this.next = this.scope = this.f = null
    }

    Xc.prototype.set = function (a, b) {
        this.f = a;
        this.scope = b;
        this.next = null
    };
    Xc.prototype.reset = function () {
        this.next = this.scope = this.f = null
    };

    function Zc(a, b) {
        $c || ad();
        bd || ($c(), bd = !0);
        cd.add(a, b)
    }

    var $c;

    function ad() {
        if (x.Promise && x.Promise.resolve) {
            var a = x.Promise.resolve(void 0);
            $c = function () {
                a.then(dd)
            }
        } else $c = function () {
            var b = dd;
            !A(x.setImmediate) || x.Window && x.Window.prototype && -1 == bb.indexOf("Edge") && x.Window.prototype.setImmediate == x.setImmediate ? (Uc || (Uc = Vc()), Uc(b)) : x.setImmediate(b)
        }
    }

    var bd = !1, cd = new Wc;

    function dd() {
        for (var a; a = cd.remove();) {
            try {
                a.f.call(a.scope)
            } catch (b) {
                Tc(b)
            }
            Sc(Yc, a)
        }
        bd = !1
    }

    function M(a) {
        this.f = 0;
        this.m = void 0;
        this.i = this.g = this.h = null;
        this.j = this.l = !1;
        if (a != xa) try {
            var b = this;
            a.call(void 0, function (c) {
                ed(b, 2, c)
            }, function (c) {
                ed(b, 3, c)
            })
        } catch (c) {
            ed(this, 3, c)
        }
    }

    function fd() {
        this.next = this.context = this.onRejected = this.g = this.f = null;
        this.h = !1
    }

    fd.prototype.reset = function () {
        this.context = this.onRejected = this.g = this.f = null;
        this.h = !1
    };
    var gd = new Rc(function () {
        return new fd
    }, function (a) {
        a.reset()
    });

    function hd(a, b, c) {
        var d = gd.get();
        d.g = a;
        d.onRejected = b;
        d.context = c;
        return d
    }

    function id(a) {
        return new M(function (b, c) {
            c(a)
        })
    }

    M.prototype.then = function (a, b, c) {
        return jd(this, A(a) ? a : null, A(b) ? b : null, c)
    };
    M.prototype.$goog_Thenable = !0;

    function kd(a, b) {
        return jd(a, null, b, void 0)
    }

    M.prototype.cancel = function (a) {
        if (0 == this.f) {
            var b = new ld(a);
            Zc(function () {
                md(this, b)
            }, this)
        }
    };

    function md(a, b) {
        if (0 == a.f) if (a.h) {
            var c = a.h;
            if (c.g) {
                for (var d = 0, e = null, f = null, g = c.g; g && (g.h || (d++, g.f == a && (e = g), !(e && 1 < d))); g = g.next) e || (f = g);
                e && (0 == c.f && 1 == d ? md(c, b) : (f ? (d = f, d.next == c.i && (c.i = d), d.next = d.next.next) : nd(c), od(c, e, 3, b)))
            }
            a.h = null
        } else ed(a, 3, b)
    }

    function pd(a, b) {
        a.g || 2 != a.f && 3 != a.f || qd(a);
        a.i ? a.i.next = b : a.g = b;
        a.i = b
    }

    function jd(a, b, c, d) {
        var e = hd(null, null, null);
        e.f = new M(function (f, g) {
            e.g = b ? function (h) {
                try {
                    var k = b.call(d, h);
                    f(k)
                } catch (l) {
                    g(l)
                }
            } : f;
            e.onRejected = c ? function (h) {
                try {
                    var k = c.call(d, h);
                    void 0 === k && h instanceof ld ? g(h) : f(k)
                } catch (l) {
                    g(l)
                }
            } : g
        });
        e.f.h = a;
        pd(a, e);
        return e.f
    }

    M.prototype.w = function (a) {
        this.f = 0;
        ed(this, 2, a)
    };
    M.prototype.A = function (a) {
        this.f = 0;
        ed(this, 3, a)
    };

    function ed(a, b, c) {
        if (0 == a.f) {
            a === c && (b = 3, c = new TypeError("Promise cannot resolve to itself"));
            a.f = 1;
            a:{
                var d = c, e = a.w, f = a.A;
                if (d instanceof M) {
                    pd(d, hd(e || xa, f || null, a));
                    var g = !0
                } else if (Qc(d)) d.then(e, f, a), g = !0; else {
                    if (Ca(d)) try {
                        var h = d.then;
                        if (A(h)) {
                            rd(d, h, e, f, a);
                            g = !0;
                            break a
                        }
                    } catch (k) {
                        f.call(a, k);
                        g = !0;
                        break a
                    }
                    g = !1
                }
            }
            g || (a.m = c, a.f = b, a.h = null, qd(a), 3 != b || c instanceof ld || sd(a, c))
        }
    }

    function rd(a, b, c, d, e) {
        function f(k) {
            h || (h = !0, d.call(e, k))
        }

        function g(k) {
            h || (h = !0, c.call(e, k))
        }

        var h = !1;
        try {
            b.call(a, g, f)
        } catch (k) {
            f(k)
        }
    }

    function qd(a) {
        a.l || (a.l = !0, Zc(a.B, a))
    }

    function nd(a) {
        var b = null;
        a.g && (b = a.g, a.g = b.next, b.next = null);
        a.g || (a.i = null);
        return b
    }

    M.prototype.B = function () {
        for (var a; a = nd(this);) od(this, a, this.f, this.m);
        this.l = !1
    };

    function od(a, b, c, d) {
        if (3 == c && b.onRejected && !b.h) for (; a && a.j; a = a.h) a.j = !1;
        if (b.f) b.f.h = null, td(b, c, d); else try {
            b.h ? b.g.call(b.context) : td(b, c, d)
        } catch (e) {
            ud.call(null, e)
        }
        Sc(gd, b)
    }

    function td(a, b, c) {
        2 == b ? a.g.call(a.context, c) : a.onRejected && a.onRejected.call(a.context, c)
    }

    function sd(a, b) {
        a.j = !0;
        Zc(function () {
            a.j && ud.call(null, b)
        })
    }

    var ud = Tc;

    function ld(a) {
        F.call(this, a)
    }

    E(ld, F);
    ld.prototype.name = "cancel";

    function vd() {
        this.g = [];
        this.f = -1
    }

    vd.prototype.set = function (a, b) {
        b = void 0 === b ? !0 : b;
        0 <= a && 52 > a && 0 === a % 1 && this.g[a] != b && (this.g[a] = b, this.f = -1)
    };
    vd.prototype.get = function (a) {
        return !!this.g[a]
    };

    function wd(a) {
        -1 == a.f && (a.f = Oa(a.g, function (b, c, d) {
            return c ? b + Math.pow(2, d) : b
        }, 0));
        return a.f
    }

    function xd() {
        this.g = -1
    }

    function yd() {
        this.g = 64;
        this.f = [];
        this.l = [];
        this.m = [];
        this.i = [];
        this.i[0] = 128;
        for (var a = 1; a < this.g; ++a) this.i[a] = 0;
        this.j = this.h = 0;
        this.reset()
    }

    E(yd, xd);
    yd.prototype.reset = function () {
        this.f[0] = 1732584193;
        this.f[1] = 4023233417;
        this.f[2] = 2562383102;
        this.f[3] = 271733878;
        this.f[4] = 3285377520;
        this.j = this.h = 0
    };

    function zd(a, b, c) {
        c || (c = 0);
        var d = a.m;
        if ("string" === typeof b) for (var e = 0; 16 > e; e++) d[e] = b.charCodeAt(c) << 24 | b.charCodeAt(c + 1) << 16 | b.charCodeAt(c + 2) << 8 | b.charCodeAt(c + 3), c += 4; else for (e = 0; 16 > e; e++) d[e] = b[c] << 24 | b[c + 1] << 16 | b[c + 2] << 8 | b[c + 3], c += 4;
        for (e = 16; 80 > e; e++) {
            var f = d[e - 3] ^ d[e - 8] ^ d[e - 14] ^ d[e - 16];
            d[e] = (f << 1 | f >>> 31) & 4294967295
        }
        b = a.f[0];
        c = a.f[1];
        var g = a.f[2], h = a.f[3], k = a.f[4];
        for (e = 0; 80 > e; e++) {
            if (40 > e) if (20 > e) {
                f = h ^ c & (g ^ h);
                var l = 1518500249
            } else f = c ^ g ^ h, l = 1859775393; else 60 > e ? (f = c & g | h & (c | g), l = 2400959708) :
                (f = c ^ g ^ h, l = 3395469782);
            f = (b << 5 | b >>> 27) + f + k + l + d[e] & 4294967295;
            k = h;
            h = g;
            g = (c << 30 | c >>> 2) & 4294967295;
            c = b;
            b = f
        }
        a.f[0] = a.f[0] + b & 4294967295;
        a.f[1] = a.f[1] + c & 4294967295;
        a.f[2] = a.f[2] + g & 4294967295;
        a.f[3] = a.f[3] + h & 4294967295;
        a.f[4] = a.f[4] + k & 4294967295
    }

    yd.prototype.update = function (a, b) {
        if (null != a) {
            void 0 === b && (b = a.length);
            for (var c = b - this.g, d = 0, e = this.l, f = this.h; d < b;) {
                if (0 == f) for (; d <= c;) zd(this, a, d), d += this.g;
                if ("string" === typeof a) for (; d < b;) {
                    if (e[f] = a.charCodeAt(d), ++f, ++d, f == this.g) {
                        zd(this, e);
                        f = 0;
                        break
                    }
                } else for (; d < b;) if (e[f] = a[d], ++f, ++d, f == this.g) {
                    zd(this, e);
                    f = 0;
                    break
                }
            }
            this.h = f;
            this.j += b
        }
    };
    yd.prototype.digest = function () {
        var a = [], b = 8 * this.j;
        56 > this.h ? this.update(this.i, 56 - this.h) : this.update(this.i, this.g - (this.h - 56));
        for (var c = this.g - 1; 56 <= c; c--) this.l[c] = b & 255, b /= 256;
        zd(this, this.l);
        for (c = b = 0; 5 > c; c++) for (var d = 24; 0 <= d; d -= 8) a[b] = this.f[c] >> d & 255, ++b;
        return a
    };

    function N() {
        this.g = this.g;
        this.B = this.B
    }

    N.prototype.g = !1;
    N.prototype.dispose = function () {
        this.g || (this.g = !0, this.o())
    };

    function Ad(a, b) {
        a.g ? b() : (a.B || (a.B = []), a.B.push(b))
    }

    N.prototype.o = function () {
        if (this.B) for (; this.B.length;) this.B.shift()()
    };

    function Bd(a) {
        a && "function" == typeof a.dispose && a.dispose()
    }

    function Cd(a) {
        for (var b = 0, c = arguments.length; b < c; ++b) {
            var d = arguments[b];
            Ba(d) ? Cd.apply(null, d) : Bd(d)
        }
    }

    function Dd(a) {
        var b = [];
        Ed(new Fd, a, b);
        return b.join("")
    }

    function Fd() {
    }

    function Ed(a, b, c) {
        if (null == b) c.push("null"); else {
            if ("object" == typeof b) {
                if (Aa(b)) {
                    var d = b;
                    b = d.length;
                    c.push("[");
                    for (var e = "", f = 0; f < b; f++) c.push(e), Ed(a, d[f], c), e = ",";
                    c.push("]");
                    return
                }
                if (b instanceof String || b instanceof Number || b instanceof Boolean) b = b.valueOf(); else {
                    c.push("{");
                    e = "";
                    for (d in b) Object.prototype.hasOwnProperty.call(b, d) && (f = b[d], "function" != typeof f && (c.push(e), Gd(d, c), c.push(":"), Ed(a, f, c), e = ","));
                    c.push("}");
                    return
                }
            }
            switch (typeof b) {
                case "string":
                    Gd(b, c);
                    break;
                case "number":
                    c.push(isFinite(b) &&
                    !isNaN(b) ? String(b) : "null");
                    break;
                case "boolean":
                    c.push(String(b));
                    break;
                case "function":
                    c.push("null");
                    break;
                default:
                    throw Error("Unknown type: " + typeof b);
            }
        }
    }

    var Hd = {
        '"': '\\"',
        "\\": "\\\\",
        "/": "\\/",
        "\b": "\\b",
        "\f": "\\f",
        "\n": "\\n",
        "\r": "\\r",
        "\t": "\\t",
        "\x0B": "\\u000b"
    }, Id = /\uffff/.test("\uffff") ? /[\\"\x00-\x1f\x7f-\uffff]/g : /[\\"\x00-\x1f\x7f-\xff]/g;

    function Gd(a, b) {
        b.push('"', a.replace(Id, function (c) {
            var d = Hd[c];
            d || (d = "\\u" + (c.charCodeAt(0) | 65536).toString(16).substr(1), Hd[c] = d);
            return d
        }), '"')
    }

    function O(a) {
        N.call(this);
        this.l = 1;
        this.i = [];
        this.j = 0;
        this.f = [];
        this.h = {};
        this.m = !!a
    }

    E(O, N);
    n = O.prototype;
    n.subscribe = function (a, b, c) {
        var d = this.h[a];
        d || (d = this.h[a] = []);
        var e = this.l;
        this.f[e] = a;
        this.f[e + 1] = b;
        this.f[e + 2] = c;
        this.l = e + 3;
        d.push(e);
        return e
    };

    function Jd(a, b, c, d) {
        if (b = a.h[b]) {
            var e = a.f;
            (b = Pa(b, function (f) {
                return e[f + 1] == c && e[f + 2] == d
            })) && a.N(b)
        }
    }

    n.N = function (a) {
        var b = this.f[a];
        if (b) {
            var c = this.h[b];
            0 != this.j ? (this.i.push(a), this.f[a + 1] = xa) : (c && Qa(c, a), delete this.f[a], delete this.f[a + 1], delete this.f[a + 2])
        }
        return !!b
    };
    n.M = function (a, b) {
        var c = this.h[a];
        if (c) {
            for (var d = Array(arguments.length - 1), e = 1, f = arguments.length; e < f; e++) d[e - 1] = arguments[e];
            if (this.m) for (e = 0; e < c.length; e++) {
                var g = c[e];
                Kd(this.f[g + 1], this.f[g + 2], d)
            } else {
                this.j++;
                try {
                    for (e = 0, f = c.length; e < f; e++) g = c[e], this.f[g + 1].apply(this.f[g + 2], d)
                } finally {
                    if (this.j--, 0 < this.i.length && 0 == this.j) for (; c = this.i.pop();) this.N(c)
                }
            }
            return 0 != e
        }
        return !1
    };

    function Kd(a, b, c) {
        Zc(function () {
            a.apply(b, c)
        })
    }

    n.clear = function (a) {
        if (a) {
            var b = this.h[a];
            b && (G(b, this.N, this), delete this.h[a])
        } else this.f.length = 0, this.h = {}
    };
    n.o = function () {
        O.L.o.call(this);
        this.clear();
        this.i.length = 0
    };

    function Ld(a) {
        this.f = a
    }

    Ld.prototype.set = function (a, b) {
        void 0 === b ? this.f.remove(a) : this.f.set(a, Dd(b))
    };
    Ld.prototype.get = function (a) {
        try {
            var b = this.f.get(a)
        } catch (c) {
            return
        }
        if (null !== b) try {
            return JSON.parse(b)
        } catch (c) {
            throw"Storage: Invalid value was encountered";
        }
    };
    Ld.prototype.remove = function (a) {
        this.f.remove(a)
    };

    function Md(a) {
        this.f = a
    }

    E(Md, Ld);

    function Nd(a) {
        this.data = a
    }

    function Od(a) {
        return void 0 === a || a instanceof Nd ? a : new Nd(a)
    }

    Md.prototype.set = function (a, b) {
        Md.L.set.call(this, a, Od(b))
    };
    Md.prototype.g = function (a) {
        a = Md.L.get.call(this, a);
        if (void 0 === a || a instanceof Object) return a;
        throw"Storage: Invalid value was encountered";
    };
    Md.prototype.get = function (a) {
        if (a = this.g(a)) {
            if (a = a.data, void 0 === a) throw"Storage: Invalid value was encountered";
        } else a = void 0;
        return a
    };

    function Pd(a) {
        this.f = a
    }

    E(Pd, Md);
    Pd.prototype.set = function (a, b, c) {
        if (b = Od(b)) {
            if (c) {
                if (c < D()) {
                    Pd.prototype.remove.call(this, a);
                    return
                }
                b.expiration = c
            }
            b.creation = D()
        }
        Pd.L.set.call(this, a, b)
    };
    Pd.prototype.g = function (a) {
        var b = Pd.L.g.call(this, a);
        if (b) {
            var c = b.creation, d = b.expiration;
            if (d && d < D() || c && c > D()) Pd.prototype.remove.call(this, a); else return b
        }
    };

    function Qd() {
    }

    function Rd() {
    }

    E(Rd, Qd);
    Rd.prototype.clear = function () {
        var a = Lc(this.G(!0)), b = this;
        G(a, function (c) {
            b.remove(c)
        })
    };

    function Sd(a) {
        this.f = a
    }

    E(Sd, Rd);
    n = Sd.prototype;
    n.isAvailable = function () {
        if (!this.f) return !1;
        try {
            return this.f.setItem("__sak", "1"), this.f.removeItem("__sak"), !0
        } catch (a) {
            return !1
        }
    };
    n.set = function (a, b) {
        try {
            this.f.setItem(a, b)
        } catch (c) {
            if (0 == this.f.length) throw"Storage mechanism: Storage disabled";
            throw"Storage mechanism: Quota exceeded";
        }
    };
    n.get = function (a) {
        a = this.f.getItem(a);
        if ("string" !== typeof a && null !== a) throw"Storage mechanism: Invalid value was encountered";
        return a
    };
    n.remove = function (a) {
        this.f.removeItem(a)
    };
    n.G = function (a) {
        var b = 0, c = this.f, d = new Ic;
        d.next = function () {
            if (b >= c.length) throw Hc;
            var e = c.key(b++);
            if (a) return e;
            e = c.getItem(e);
            if ("string" !== typeof e) throw"Storage mechanism: Invalid value was encountered";
            return e
        };
        return d
    };
    n.clear = function () {
        this.f.clear()
    };
    n.key = function (a) {
        return this.f.key(a)
    };

    function Td() {
        var a = null;
        try {
            a = window.localStorage || null
        } catch (b) {
        }
        this.f = a
    }

    E(Td, Sd);

    function Ud(a, b) {
        this.g = a;
        this.f = null;
        if (Ob && !(9 <= Number(Pb))) {
            Vd || (Vd = new Mc);
            this.f = Vd.get(a);
            this.f || (b ? this.f = document.getElementById(b) : (this.f = document.createElement("userdata"), this.f.addBehavior("#default#userData"), document.body.appendChild(this.f)), Vd.set(a, this.f));
            try {
                this.f.load(this.g)
            } catch (c) {
                this.f = null
            }
        }
    }

    E(Ud, Rd);
    var Wd = {".": ".2E", "!": ".21", "~": ".7E", "*": ".2A", "'": ".27", "(": ".28", ")": ".29", "%": "."}, Vd = null;

    function Xd(a) {
        return "_" + encodeURIComponent(a).replace(/[.!~*'()%]/g, function (b) {
            return Wd[b]
        })
    }

    n = Ud.prototype;
    n.isAvailable = function () {
        return !!this.f
    };
    n.set = function (a, b) {
        this.f.setAttribute(Xd(a), b);
        Yd(this)
    };
    n.get = function (a) {
        a = this.f.getAttribute(Xd(a));
        if ("string" !== typeof a && null !== a) throw"Storage mechanism: Invalid value was encountered";
        return a
    };
    n.remove = function (a) {
        this.f.removeAttribute(Xd(a));
        Yd(this)
    };
    n.G = function (a) {
        var b = 0, c = this.f.XMLDocument.documentElement.attributes, d = new Ic;
        d.next = function () {
            if (b >= c.length) throw Hc;
            var e = c[b++];
            if (a) return decodeURIComponent(e.nodeName.replace(/\./g, "%")).substr(1);
            e = e.nodeValue;
            if ("string" !== typeof e) throw"Storage mechanism: Invalid value was encountered";
            return e
        };
        return d
    };
    n.clear = function () {
        for (var a = this.f.XMLDocument.documentElement, b = a.attributes.length; 0 < b; b--) a.removeAttribute(a.attributes[b - 1].nodeName);
        Yd(this)
    };

    function Yd(a) {
        try {
            a.f.save(a.g)
        } catch (b) {
            throw"Storage mechanism: Quota exceeded";
        }
    }

    function Zd(a, b) {
        this.g = a;
        this.f = b + "::"
    }

    E(Zd, Rd);
    Zd.prototype.set = function (a, b) {
        this.g.set(this.f + a, b)
    };
    Zd.prototype.get = function (a) {
        return this.g.get(this.f + a)
    };
    Zd.prototype.remove = function (a) {
        this.g.remove(this.f + a)
    };
    Zd.prototype.G = function (a) {
        var b = this.g.G(!0), c = this, d = new Ic;
        d.next = function () {
            for (var e = b.next(); e.substr(0, c.f.length) != c.f;) e = b.next();
            return a ? e.substr(c.f.length) : c.g.get(e)
        };
        return d
    };

    function $d(a, b) {
        1 < b.length ? a[b[0]] = b[1] : 1 === b.length && Object.assign(a, b[0])
    }

    var ae = window.yt && window.yt.config_ || window.ytcfg && window.ytcfg.data_ || {};
    y("yt.config_", ae, void 0);

    function P(a) {
        $d(ae, arguments)
    }

    function Q(a, b) {
        return a in ae ? ae[a] : b
    }

    function be() {
        return Q("ERRORS", [])
    }

    function ce() {
        return Q("PLAYER_CONFIG", {})
    }

    function de() {
        var a = ee;
        z("yt.ads.biscotti.getId_") || y("yt.ads.biscotti.getId_", a, void 0)
    }

    function fe(a) {
        y("yt.ads.biscotti.lastId_", a, void 0)
    }

    var ge = [];

    function he(a) {
        ge.forEach(function (b) {
            return b(a)
        })
    }

    function ie(a) {
        return a && window.yterr ? function () {
            try {
                return a.apply(this, arguments)
            } catch (b) {
                je(b), he(b)
            }
        } : a
    }

    function je(a) {
        var b = z("yt.logging.errors.log");
        b ? b(a, "ERROR", void 0, void 0, void 0) : (b = be(), b.push([a, "ERROR", void 0, void 0, void 0]), P("ERRORS", b))
    }

    function ke(a) {
        var b = z("yt.logging.errors.log");
        b ? b(a, "WARNING", void 0, void 0, void 0) : (b = be(), b.push([a, "WARNING", void 0, void 0, void 0]), P("ERRORS", b))
    }

    function le(a) {
        a = a.split("&");
        for (var b = {}, c = 0, d = a.length; c < d; c++) {
            var e = a[c].split("=");
            if (1 == e.length && e[0] || 2 == e.length) try {
                var f = decodeURIComponent((e[0] || "").replace(/\+/g, " ")),
                    g = decodeURIComponent((e[1] || "").replace(/\+/g, " "));
                f in b ? Aa(b[f]) ? Sa(b[f], g) : b[f] = [b[f], g] : b[f] = g
            } catch (k) {
                if ("q" != e[0]) {
                    var h = Error("Error decoding URL component");
                    h.params = {key: e[0], value: e[1]};
                    je(h)
                }
            }
        }
        return b
    }

    function me(a) {
        var b = [];
        eb(a, function (c, d) {
            var e = encodeURIComponent(String(d)), f;
            Aa(c) ? f = c : f = [c];
            G(f, function (g) {
                "" == g ? b.push(e) : b.push(e + "=" + encodeURIComponent(String(g)))
            })
        });
        return b.join("&")
    }

    function ne(a) {
        "?" == a.charAt(0) && (a = a.substr(1));
        return le(a)
    }

    function oe(a, b) {
        return pe(a, b || {}, !0)
    }

    function pe(a, b, c) {
        var d = a.split("#", 2);
        a = d[0];
        d = 1 < d.length ? "#" + d[1] : "";
        var e = a.split("?", 2);
        a = e[0];
        e = ne(e[1] || "");
        for (var f in b) !c && null !== e && f in e || (e[f] = b[f]);
        return nc(a, e) + d
    }

    function qe(a) {
        var b = re;
        a = void 0 === a ? z("yt.ads.biscotti.lastId_") || "" : a;
        b = Object.assign(se(b), te(b));
        b.ca_type = "image";
        a && (b.bid = a);
        return b
    }

    function se(a) {
        var b = {};
        b.dt = wc;
        b.flash = "0";
        a:{
            try {
                var c = a.f.top.location.href
            } catch (f) {
                a = 2;
                break a
            }
            a = c ? c === a.g.location.href ? 0 : 1 : 2
        }
        b = (b.frm = a, b);
        b.u_tz = -(new Date).getTimezoneOffset();
        var d = void 0 === d ? I : d;
        try {
            var e = d.history.length
        } catch (f) {
            e = 0
        }
        b.u_his = e;
        b.u_java = !!I.navigator && "unknown" !== typeof I.navigator.javaEnabled && !!I.navigator.javaEnabled && I.navigator.javaEnabled();
        I.screen && (b.u_h = I.screen.height, b.u_w = I.screen.width, b.u_ah = I.screen.availHeight, b.u_aw = I.screen.availWidth, b.u_cd = I.screen.colorDepth);
        I.navigator && I.navigator.plugins && (b.u_nplug = I.navigator.plugins.length);
        I.navigator && I.navigator.mimeTypes && (b.u_nmime = I.navigator.mimeTypes.length);
        return b
    }

    function te(a) {
        var b = a.f;
        try {
            var c = b.screenX;
            var d = b.screenY
        } catch (p) {
        }
        try {
            var e = b.outerWidth;
            var f = b.outerHeight
        } catch (p) {
        }
        try {
            var g = b.innerWidth;
            var h = b.innerHeight
        } catch (p) {
        }
        b = [b.screenLeft, b.screenTop, c, d, b.screen ? b.screen.availWidth : void 0, b.screen ? b.screen.availTop : void 0, e, f, g, h];
        c = a.f.top;
        try {
            var k = (c || window).document, l = "CSS1Compat" == k.compatMode ? k.documentElement : k.body;
            var m = (new $b(l.clientWidth, l.clientHeight)).round()
        } catch (p) {
            m = new $b(-12245933, -12245933)
        }
        k = m;
        m = {};
        l = new vd;
        x.SVGElement &&
        x.document.createElementNS && l.set(0);
        c = uc();
        c["allow-top-navigation-by-user-activation"] && l.set(1);
        c["allow-popups-to-escape-sandbox"] && l.set(2);
        x.crypto && x.crypto.subtle && l.set(3);
        x.TextDecoder && x.TextEncoder && l.set(4);
        l = wd(l);
        m.bc = l;
        m.bih = k.height;
        m.biw = k.width;
        m.brdim = b.join();
        a = a.g;
        return m.vis = {
            visible: 1,
            hidden: 2,
            prerender: 3,
            preview: 4,
            unloaded: 5
        }[a.visibilityState || a.webkitVisibilityState || a.mozVisibilityState || ""] || 0, m.wgl = !!I.WebGLRenderingContext, m
    }

    var re = new function () {
        var a = window.document;
        this.f = window;
        this.g = a
    };
    y("yt.ads_.signals_.getAdSignalsString", function (a) {
        return me(qe(a))
    }, void 0);
    D();

    function R(a) {
        a = ue(a);
        return "string" === typeof a && "false" === a ? !1 : !!a
    }

    function ve(a, b) {
        var c = ue(a);
        return void 0 === c && void 0 !== b ? b : Number(c || 0)
    }

    function ue(a) {
        var b = Q("EXPERIMENTS_FORCED_FLAGS", {});
        return void 0 !== b[a] ? b[a] : Q("EXPERIMENT_FLAGS", {})[a]
    }

    var we = void 0 !== XMLHttpRequest ? function () {
        return new XMLHttpRequest
    } : void 0 !== ActiveXObject ? function () {
        return new ActiveXObject("Microsoft.XMLHTTP")
    } : null;

    function xe() {
        if (!we) return null;
        var a = we();
        return "open" in a ? a : null
    }

    function ye(a) {
        switch (a && "status" in a ? a.status : -1) {
            case 200:
            case 201:
            case 202:
            case 203:
            case 204:
            case 205:
            case 206:
            case 304:
                return !0;
            default:
                return !1
        }
    }

    function S(a, b) {
        A(a) && (a = ie(a));
        return window.setTimeout(a, b)
    }

    function T(a) {
        window.clearTimeout(a)
    }

    var ze = {
            Authorization: "AUTHORIZATION",
            "X-Goog-Visitor-Id": "SANDBOXED_VISITOR_ID",
            "X-YouTube-Client-Name": "INNERTUBE_CONTEXT_CLIENT_NAME",
            "X-YouTube-Client-Version": "INNERTUBE_CONTEXT_CLIENT_VERSION",
            "X-YouTube-Device": "DEVICE",
            "X-Youtube-Identity-Token": "ID_TOKEN",
            "X-YouTube-Page-CL": "PAGE_CL",
            "X-YouTube-Page-Label": "PAGE_BUILD_LABEL",
            "X-YouTube-Variants-Checksum": "VARIANTS_CHECKSUM"
        },
        Ae = "app debugcss debugjs expflag force_ad_params force_viral_ad_response_params forced_experiments innertube_snapshots innertube_goldens internalcountrycode internalipoverride absolute_experiments conditional_experiments sbb sr_bns_address".split(" "),
        Be = !1;

    function Ce(a, b) {
        b = void 0 === b ? {} : b;
        if (!c) var c = window.location.href;
        var d = L(1, a), e = J(L(3, a));
        d && e ? (d = c, c = a.match(kc), d = d.match(kc), c = c[3] == d[3] && c[1] == d[1] && c[4] == d[4]) : c = e ? J(L(3, c)) == e && (Number(L(4, c)) || null) == (Number(L(4, a)) || null) : !0;
        d = R("web_ajax_ignore_global_headers_if_set");
        for (var f in ze) e = Q(ze[f]), !e || !c && !De(a, f) || d && void 0 !== b[f] || (b[f] = e);
        if (c || De(a, "X-YouTube-Utc-Offset")) b["X-YouTube-Utc-Offset"] = String(-(new Date).getTimezoneOffset());
        (c || De(a, "X-YouTube-Time-Zone")) && (f = "undefined" != typeof Intl ?
            (new Intl.DateTimeFormat).resolvedOptions().timeZone : null) && (b["X-YouTube-Time-Zone"] = f);
        if (c || De(a, "X-YouTube-Ad-Signals")) b["X-YouTube-Ad-Signals"] = me(qe(void 0));
        return b
    }

    function Ee(a) {
        var b = window.location.search, c = J(L(3, a)), d = J(L(5, a));
        d = (c = c && (c.endsWith("youtube.com") || c.endsWith("youtube-nocookie.com"))) && d && d.startsWith("/api/");
        if (!c || d) return a;
        var e = ne(b), f = {};
        G(Ae, function (g) {
            e[g] && (f[g] = e[g])
        });
        return pe(a, f || {}, !1)
    }

    function De(a, b) {
        var c = Q("CORS_HEADER_WHITELIST") || {}, d = J(L(3, a));
        return d ? (c = c[d]) ? 0 <= La(c, b) : !1 : !0
    }

    function Fe(a, b) {
        if (window.fetch && "XML" != b.format) {
            var c = {method: b.method || "GET", credentials: "same-origin"};
            b.headers && (c.headers = b.headers);
            a = Ge(a, b);
            var d = He(a, b);
            d && (c.body = d);
            b.withCredentials && (c.credentials = "include");
            var e = !1, f;
            fetch(a, c).then(function (g) {
                if (!e) {
                    e = !0;
                    f && T(f);
                    var h = g.ok, k = function (l) {
                        l = l || {};
                        var m = b.context || x;
                        h ? b.onSuccess && b.onSuccess.call(m, l, g) : b.onError && b.onError.call(m, l, g);
                        b.ja && b.ja.call(m, l, g)
                    };
                    "JSON" == (b.format || "JSON") && (h || 400 <= g.status && 500 > g.status) ? g.json().then(k, function () {
                        k(null)
                    }) : k(null)
                }
            });
            b.qa && 0 < b.timeout && (f = S(function () {
                e || (e = !0, T(f), b.qa.call(b.context || x))
            }, b.timeout))
        } else Ie(a, b)
    }

    function Ie(a, b) {
        var c = b.format || "JSON";
        a = Ge(a, b);
        var d = He(a, b), e = !1, f, g = Je(a, function (h) {
                if (!e) {
                    e = !0;
                    f && T(f);
                    var k = ye(h), l = null, m = 400 <= h.status && 500 > h.status, p = 500 <= h.status && 600 > h.status;
                    if (k || m || p) l = Ke(c, h, b.pb);
                    if (k) a:if (h && 204 == h.status) k = !0; else {
                        switch (c) {
                            case "XML":
                                k = 0 == parseInt(l && l.return_code, 10);
                                break a;
                            case "RAW":
                                k = !0;
                                break a
                        }
                        k = !!l
                    }
                    l = l || {};
                    m = b.context || x;
                    k ? b.onSuccess && b.onSuccess.call(m, h, l) : b.onError && b.onError.call(m, h, l);
                    b.ja && b.ja.call(m, h, l)
                }
            }, b.method, d, b.headers, b.responseType,
            b.withCredentials);
        b.O && 0 < b.timeout && (f = S(function () {
            e || (e = !0, g.abort(), T(f), b.O.call(b.context || x, g))
        }, b.timeout));
        return g
    }

    function Ge(a, b) {
        b.sb && (a = document.location.protocol + "//" + document.location.hostname + (document.location.port ? ":" + document.location.port : "") + a);
        var c = Q("XSRF_FIELD_NAME", void 0), d = b.Za;
        d && (d[c] && delete d[c], a = oe(a, d));
        return a
    }

    function He(a, b) {
        var c = Q("XSRF_FIELD_NAME", void 0), d = Q("XSRF_TOKEN", void 0), e = b.postBody || "", f = b.C,
            g = Q("XSRF_FIELD_NAME", void 0), h;
        b.headers && (h = b.headers["Content-Type"]);
        b.rb || J(L(3, a)) && !b.withCredentials && J(L(3, a)) != document.location.hostname || "POST" != b.method || h && "application/x-www-form-urlencoded" != h || b.C && b.C[g] || (f || (f = {}), f[c] = d);
        f && "string" === typeof e && (e = ne(e), ob(e, f), e = b.ra && "JSON" == b.ra ? JSON.stringify(e) : mc(e));
        f = e || f && !ib(f);
        !Be && f && "POST" != b.method && (Be = !0, je(Error("AJAX request with postData should use POST")));
        return e
    }

    function Ke(a, b, c) {
        var d = null;
        switch (a) {
            case "JSON":
                a = b.responseText;
                b = b.getResponseHeader("Content-Type") || "";
                a && 0 <= b.indexOf("json") && (d = JSON.parse(a));
                break;
            case "XML":
                if (b = (b = b.responseXML) ? Le(b) : null) d = {}, G(b.getElementsByTagName("*"), function (e) {
                    d[e.tagName] = Me(e)
                })
        }
        c && Ne(d);
        return d
    }

    function Ne(a) {
        if (Ca(a)) for (var b in a) {
            var c;
            (c = "html_content" == b) || (c = b.length - 5, c = 0 <= c && b.indexOf("_html", c) == c);
            if (c) {
                c = b;
                var d = Ib(a[b], null);
                a[c] = d
            } else Ne(a[b])
        }
    }

    function Le(a) {
        return a ? (a = ("responseXML" in a ? a.responseXML : a).getElementsByTagName("root")) && 0 < a.length ? a[0] : null : null
    }

    function Me(a) {
        var b = "";
        G(a.childNodes, function (c) {
            b += c.nodeValue
        });
        return b
    }

    function Je(a, b, c, d, e, f, g) {
        function h() {
            4 == (k && "readyState" in k ? k.readyState : 0) && b && ie(b)(k)
        }

        c = void 0 === c ? "GET" : c;
        d = void 0 === d ? "" : d;
        var k = xe();
        if (!k) return null;
        "onloadend" in k ? k.addEventListener("loadend", h, !1) : k.onreadystatechange = h;
        R("debug_forward_web_query_parameters") && (a = Ee(a));
        k.open(c, a, !0);
        f && (k.responseType = f);
        g && (k.withCredentials = !0);
        c = "POST" == c && (void 0 === window.FormData || !(d instanceof FormData));
        if (e = Ce(a, e)) for (var l in e) k.setRequestHeader(l, e[l]), "content-type" == l.toLowerCase() && (c = !1);
        c && k.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
        k.send(d);
        return k
    }

    var Oe = {}, Pe = 0;

    function Qe(a, b, c, d, e) {
        e = void 0 === e ? "" : e;
        a && (c && (c = bb, c = !(c && 0 <= c.toLowerCase().indexOf("cobalt"))), c ? a && (a instanceof H || (a = "object" == typeof a && a.J ? a.I() : String(a), Cb.test(a) || (a = "about:invalid#zClosurez"), a = new H(zb, a)), b = Bb(a), "about:invalid#zClosurez" === b ? a = "" : (b instanceof Eb ? a = b : (d = "object" == typeof b, a = null, d && b.ha && (a = b.ea()), b = Ua(d && b.J ? b.I() : String(b)), a = Ib(b, a)), a = Hb(a).toString(), a = encodeURIComponent(String(Dd(a)))), /^[\s\xa0]*$/.test(a) || (a = dc("IFRAME", {
            src: 'javascript:"<body><img src=\\""+' + a +
                '+"\\"></body>"', style: "display:none"
        }), (9 == a.nodeType ? a : a.ownerDocument || a.document).body.appendChild(a))) : e ? Je(a, b, "POST", e, d) : Q("USE_NET_AJAX_FOR_PING_TRANSPORT", !1) || d ? Je(a, b, "GET", "", d) : Re(a, b) || Se(a, b))
    }

    function Re(a, b) {
        var c = ae.EXPERIMENT_FLAGS;
        if (!c || !c.web_use_beacon_api_for_ad_click_server_pings) return !1;
        c = J(L(5, a));
        return c && -1 != c.indexOf("/aclk") && "1" === pc(a, "ae") && "1" === pc(a, "act") ? Te(a) ? (b && b(), !0) : !1 : !1
    }

    function Te(a, b) {
        try {
            if (window.navigator && window.navigator.sendBeacon && window.navigator.sendBeacon(a, void 0 === b ? "" : b)) return !0
        } catch (c) {
        }
        return !1
    }

    function Se(a, b) {
        var c = new Image, d = "" + Pe++;
        Oe[d] = c;
        c.onload = c.onerror = function () {
            b && Oe[d] && b();
            delete Oe[d]
        };
        c.src = a
    }

    function Ue(a, b) {
        for (var c = [], d = 1; d < arguments.length; ++d) c[d - 1] = arguments[d];
        d = Error.call(this, a);
        this.message = d.message;
        "stack" in d && (this.stack = d.stack);
        this.args = [].concat(c instanceof Array ? c : ba(q(c)))
    }

    t(Ue, Error);
    var Ve = new Set, We = 0;

    function Xe(a) {
        var b = z("yt.logging.errors.log");
        b ? b(a, "ERROR", void 0, void 0, void 0, void 0) : (b = be(), b.push([a, "ERROR", void 0, void 0, void 0, void 0]), P("ERRORS", b))
    }

    function Ye(a) {
        var b = z("yt.logging.errors.log");
        b ? b(a, "WARNING", void 0, void 0, void 0, void 0) : (b = be(), b.push([a, "WARNING", void 0, void 0, void 0, void 0]), P("ERRORS", b))
    }

    function Ze(a, b, c, d) {
        c += "." + a;
        a = String(JSON.stringify(b)).substr(0, 500);
        d[c] = a;
        return c.length + a.length
    }

    function $e(a, b, c, d, e, f) {
        b = void 0 === b ? "ERROR" : b;
        f = void 0 === f ? {} : f;
        f.name = c || Q("INNERTUBE_CONTEXT_CLIENT_NAME", 1);
        f.version = d || Q("INNERTUBE_CONTEXT_CLIENT_VERSION", void 0);
        c = f || {};
        d = void 0 === b ? "ERROR" : b;
        d = void 0 === d ? "ERROR" : d;
        f = window && window.yterr || !1;
        if (a && f && !(5 <= We) && (R("console_log_js_exceptions") && (f = [], f.push("Name: " + a.name), f.push("Message: " + a.message), a.hasOwnProperty("params") && f.push("Error Params: " + JSON.stringify(a.params)), f.push("File name: " + a.fileName), f.push("Stacktrace: " + a.stack),
            window.console.log(f.join("\n"), a)), 0 !== a.f)) {
            f = a.g;
            b = a.columnNumber;
            if (a.args && a.args.length) for (var g = 0, h = 0; h < a.args.length; h++) {
                e = a.args[h];
                var k = "params." + h;
                g += k.length;
                if (e) if (Array.isArray(e)) for (var l = c, m = 0; m < e.length && !(e[m] && (g += Ze(m, e[m], k, l), 500 < g)); m++) ; else if ("object" === typeof e) for (l in l = void 0, m = c, e) {
                    if (e[l] && (g += Ze(l, e[l], k, m), 500 < g)) break
                } else c[k] = String(JSON.stringify(e)).substring(0, 500), g += c[k].length; else c[k] = String(JSON.stringify(e)).substring(0, 500), g += c[k].length;
                if (500 <=
                    g) break
            } else if (a.hasOwnProperty("params")) if (e = a.params, "object" === typeof a.params) for (h in k = 0, e) {
                if (e[h] && (g = "params." + h, l = String(JSON.stringify(e[h])).substr(0, 500), c[g] = l, k += g.length + l.length, 500 < k)) break
            } else c.params = String(JSON.stringify(e)).substr(0, 500);
            a = Wb(a);
            (f = f || a.stack) || (f = "Not available");
            h = a.lineNumber.toString();
            isNaN(h) || !b || isNaN(b) || (h = h + ":" + b);
            window.yterr && A(window.yterr) && window.yterr(a);
            if (!(Ve.has(a.message) || 0 <= f.indexOf("/YouTubeCenter.js") || 0 <= f.indexOf("/mytube.js"))) {
                d =
                    {
                        Za: {
                            a: "logerror",
                            t: "jserror",
                            type: a.name,
                            msg: a.message.substr(0, 250),
                            line: h,
                            level: d,
                            "client.name": c.name
                        }, C: {url: Q("PAGE_NAME", window.location.href), file: a.fileName}, method: "POST"
                    };
                c.version && (d["client.version"] = c.version);
                if (d.C) {
                    f && (d.C.stack = f);
                    f = q(Object.keys(c));
                    for (b = f.next(); !b.done; b = f.next()) b = b.value, d.C["client." + b] = c[b];
                    if (c = Q("LATEST_ECATCHER_SERVICE_TRACKING_PARAMS", void 0)) for (f = q(Object.keys(c)), b = f.next(); !b.done; b = f.next()) b = b.value, d.C[b] = c[b]
                }
                Ie(Q("ECATCHER_REPORT_HOST", "") +
                    "/error_204", d);
                Ve.add(a.message);
                We++
            }
        }
    }

    var af = window.yt && window.yt.msgs_ || window.ytcfg && window.ytcfg.msgs || {};
    y("yt.msgs_", af, void 0);

    function bf(a) {
        $d(af, arguments)
    }

    function cf(a) {
        a && (a.dataset ? a.dataset[df("loaded")] = "true" : a.setAttribute("data-loaded", "true"))
    }

    function ef(a, b) {
        return a ? a.dataset ? a.dataset[df(b)] : a.getAttribute("data-" + b) : null
    }

    var ff = {};

    function df(a) {
        return ff[a] || (ff[a] = String(a).replace(/\-([a-z])/g, function (b, c) {
            return c.toUpperCase()
        }))
    }

    var gf = z("ytPubsubPubsubInstance") || new O;
    O.prototype.subscribe = O.prototype.subscribe;
    O.prototype.unsubscribeByKey = O.prototype.N;
    O.prototype.publish = O.prototype.M;
    O.prototype.clear = O.prototype.clear;
    y("ytPubsubPubsubInstance", gf, void 0);
    var hf = z("ytPubsubPubsubSubscribedKeys") || {};
    y("ytPubsubPubsubSubscribedKeys", hf, void 0);
    var jf = z("ytPubsubPubsubTopicToKeys") || {};
    y("ytPubsubPubsubTopicToKeys", jf, void 0);
    var kf = z("ytPubsubPubsubIsSynchronous") || {};
    y("ytPubsubPubsubIsSynchronous", kf, void 0);

    function lf(a, b) {
        var c = mf();
        if (c) {
            var d = c.subscribe(a, function () {
                var e = arguments;
                var f = function () {
                    hf[d] && b.apply(window, e)
                };
                try {
                    kf[a] ? f() : S(f, 0)
                } catch (g) {
                    je(g)
                }
            }, void 0);
            hf[d] = !0;
            jf[a] || (jf[a] = []);
            jf[a].push(d);
            return d
        }
        return 0
    }

    function nf(a) {
        var b = mf();
        b && ("number" === typeof a ? a = [a] : "string" === typeof a && (a = [parseInt(a, 10)]), G(a, function (c) {
            b.unsubscribeByKey(c);
            delete hf[c]
        }))
    }

    function of(a, b) {
        var c = mf();
        c && c.publish.apply(c, arguments)
    }

    function pf(a) {
        var b = mf();
        if (b) if (b.clear(a), a) qf(a); else for (var c in jf) qf(c)
    }

    function mf() {
        return z("ytPubsubPubsubInstance")
    }

    function qf(a) {
        jf[a] && (a = jf[a], G(a, function (b) {
            hf[b] && delete hf[b]
        }), a.length = 0)
    }

    var rf = /\.vflset|-vfl[a-zA-Z0-9_+=-]+/, sf = /-[a-zA-Z]{2,3}_[a-zA-Z]{2,3}(?=(\/|$))/;

    function tf(a, b, c) {
        c = void 0 === c ? null : c;
        if (window.spf && spf.script) {
            c = "";
            if (a) {
                var d = a.indexOf("jsbin/"), e = a.lastIndexOf(".js"), f = d + 6;
                -1 < d && -1 < e && e > f && (c = a.substring(f, e), c = c.replace(rf, ""), c = c.replace(sf, ""), c = c.replace("debug-", ""), c = c.replace("tracing-", ""))
            }
            spf.script.load(a, c, b)
        } else uf(a, b, c)
    }

    function uf(a, b, c) {
        c = void 0 === c ? null : c;
        var d = vf(a), e = document.getElementById(d), f = e && ef(e, "loaded"), g = e && !f;
        f ? b && b() : (b && (f = lf(d, b), b = "" + Da(b), wf[b] = f), g || (e = xf(a, d, function () {
            ef(e, "loaded") || (cf(e), of(d), S(Ja(pf, d), 0))
        }, c)))
    }

    function xf(a, b, c, d) {
        d = void 0 === d ? null : d;
        var e = ec(document, "SCRIPT");
        e.id = b;
        e.onload = function () {
            c && setTimeout(c, 0)
        };
        e.onreadystatechange = function () {
            switch (e.readyState) {
                case "loaded":
                case "complete":
                    e.onload()
            }
        };
        d && e.setAttribute("nonce", d);
        Lb(e, ic(a));
        a = document.getElementsByTagName("head")[0] || document.body;
        a.insertBefore(e, a.firstChild);
        return e
    }

    function yf(a) {
        a = vf(a);
        var b = document.getElementById(a);
        b && (pf(a), b.parentNode.removeChild(b))
    }

    function zf(a, b) {
        if (a && b) {
            var c = "" + Da(b);
            (c = wf[c]) && nf(c)
        }
    }

    function vf(a) {
        var b = document.createElement("a");
        Kb(b, a);
        a = b.href.replace(/^[a-zA-Z]+:\/\//, "//");
        return "js-" + Nb(a)
    }

    var wf = {};

    function Af() {
    }

    function Bf(a, b) {
        return Cf(a, 1, b)
    }

    function Df() {
    }

    t(Df, Af);

    function Cf(a, b, c) {
        isNaN(c) && (c = void 0);
        var d = z("yt.scheduler.instance.addJob");
        return d ? d(a, b, c) : void 0 === c ? (a(), NaN) : S(a, c || 0)
    }

    Df.prototype.start = function () {
        var a = z("yt.scheduler.instance.start");
        a && a()
    };
    Df.prototype.pause = function () {
        var a = z("yt.scheduler.instance.pause");
        a && a()
    };
    ya(Df);
    Df.getInstance();
    var Ef = [], Ff = !1;

    function Gf() {
        if ("1" != fb(ce(), "args", "privembed")) {
            var a = function () {
                Ff = !0;
                "google_ad_status" in window ? P("DCLKSTAT", 1) : P("DCLKSTAT", 2)
            };
            tf("//static.doubleclick.net/instream/ad_status.js", a);
            Ef.push(Bf(function () {
                Ff || "google_ad_status" in window || (zf("//static.doubleclick.net/instream/ad_status.js", a), Ff = !0, P("DCLKSTAT", 3))
            }, 5E3))
        }
    }

    function Hf() {
        return parseInt(Q("DCLKSTAT", 0), 10)
    }

    function If() {
        this.g = !1;
        this.f = null
    }

    If.prototype.initialize = function (a, b, c, d, e) {
        var f = this;
        b ? (this.g = !0, tf(b, function () {
            f.g = !1;
            if (window.botguard) Jf(f, c, d); else {
                yf(b);
                var g = Error("Unable to load Botguard");
                g.params = "from " + b;
                ke(g)
            }
        }, e)) : a && (eval(a), window.botguard ? Jf(this, c, d) : ke(Error("Unable to load Botguard from JS")))
    };

    function Jf(a, b, c) {
        try {
            a.f = new window.botguard.bg(b, c ? function () {
                return c(b)
            } : xa)
        } catch (d) {
            ke(d)
        }
    }

    If.prototype.dispose = function () {
        this.f = null
    };
    var Kf = window,
        U = Kf.ytcsi && Kf.ytcsi.now ? Kf.ytcsi.now : Kf.performance && Kf.performance.timing && Kf.performance.now ? function () {
            return Kf.performance.timing.navigationStart + Kf.performance.now()
        } : function () {
            return (new Date).getTime()
        };
    var Lf = new If, Mf = !1, Nf = 0, Of = "";

    function Pf(a) {
        R("botguard_periodic_refresh") ? Nf = U() : R("botguard_always_refresh") && (Of = a)
    }

    function Qf(a) {
        if (a) {
            if (Lf.g) return !1;
            if (R("botguard_periodic_refresh")) return 72E5 < U() - Nf;
            if (R("botguard_always_refresh")) return Of != a
        } else return !1;
        return !Mf
    }

    function Rf() {
        return !!Lf.f
    }

    function Sf(a) {
        a = void 0 === a ? {} : a;
        a = void 0 === a ? {} : a;
        return Lf.f ? Lf.f.invoke(void 0, void 0, a) : null
    }

    var Tf = 0;
    y("ytDomDomGetNextId", z("ytDomDomGetNextId") || function () {
        return ++Tf
    }, void 0);
    var Uf = {
        stopImmediatePropagation: 1,
        stopPropagation: 1,
        preventMouseEvent: 1,
        preventManipulation: 1,
        preventDefault: 1,
        layerX: 1,
        layerY: 1,
        screenX: 1,
        screenY: 1,
        scale: 1,
        rotation: 1,
        webkitMovementX: 1,
        webkitMovementY: 1
    };

    function Vf(a) {
        this.type = "";
        this.state = this.source = this.data = this.currentTarget = this.relatedTarget = this.target = null;
        this.charCode = this.keyCode = 0;
        this.metaKey = this.shiftKey = this.ctrlKey = this.altKey = !1;
        this.clientY = this.clientX = 0;
        this.changedTouches = this.touches = null;
        try {
            if (a = a || window.event) {
                this.event = a;
                for (var b in a) b in Uf || (this[b] = a[b]);
                var c = a.target || a.srcElement;
                c && 3 == c.nodeType && (c = c.parentNode);
                this.target = c;
                var d = a.relatedTarget;
                if (d) try {
                    d = d.nodeName ? d : null
                } catch (e) {
                    d = null
                } else "mouseover" ==
                this.type ? d = a.fromElement : "mouseout" == this.type && (d = a.toElement);
                this.relatedTarget = d;
                this.clientX = void 0 != a.clientX ? a.clientX : a.pageX;
                this.clientY = void 0 != a.clientY ? a.clientY : a.pageY;
                this.keyCode = a.keyCode ? a.keyCode : a.which;
                this.charCode = a.charCode || ("keypress" == this.type ? this.keyCode : 0);
                this.altKey = a.altKey;
                this.ctrlKey = a.ctrlKey;
                this.shiftKey = a.shiftKey;
                this.metaKey = a.metaKey;
                this.f = a.pageX;
                this.g = a.pageY
            }
        } catch (e) {
        }
    }

    function Wf(a) {
        if (document.body && document.documentElement) {
            var b = document.body.scrollTop + document.documentElement.scrollTop;
            a.f = a.clientX + (document.body.scrollLeft + document.documentElement.scrollLeft);
            a.g = a.clientY + b
        }
    }

    Vf.prototype.preventDefault = function () {
        this.event && (this.event.returnValue = !1, this.event.preventDefault && this.event.preventDefault())
    };
    Vf.prototype.stopPropagation = function () {
        this.event && (this.event.cancelBubble = !0, this.event.stopPropagation && this.event.stopPropagation())
    };
    Vf.prototype.stopImmediatePropagation = function () {
        this.event && (this.event.cancelBubble = !0, this.event.stopImmediatePropagation && this.event.stopImmediatePropagation())
    };
    var hb = z("ytEventsEventsListeners") || {};
    y("ytEventsEventsListeners", hb, void 0);
    var Xf = z("ytEventsEventsCounter") || {count: 0};
    y("ytEventsEventsCounter", Xf, void 0);

    function Yf(a, b, c, d) {
        d = void 0 === d ? {} : d;
        a.addEventListener && ("mouseenter" != b || "onmouseenter" in document ? "mouseleave" != b || "onmouseenter" in document ? "mousewheel" == b && "MozBoxSizing" in document.documentElement.style && (b = "MozMousePixelScroll") : b = "mouseout" : b = "mouseover");
        return gb(function (e) {
            var f = "boolean" === typeof e[4] && e[4] == !!d, g = Ca(e[4]) && Ca(d) && kb(e[4], d);
            return !!e.length && e[0] == a && e[1] == b && e[2] == c && (f || g)
        })
    }

    var Zf = pb(function () {
        var a = !1;
        try {
            var b = Object.defineProperty({}, "capture", {
                get: function () {
                    a = !0
                }
            });
            window.addEventListener("test", null, b)
        } catch (c) {
        }
        return a
    });

    function V(a, b, c, d) {
        d = void 0 === d ? {} : d;
        if (!a || !a.addEventListener && !a.attachEvent) return "";
        var e = Yf(a, b, c, d);
        if (e) return e;
        e = ++Xf.count + "";
        var f = !("mouseenter" != b && "mouseleave" != b || !a.addEventListener || "onmouseenter" in document);
        var g = f ? function (h) {
            h = new Vf(h);
            if (!hc(h.relatedTarget, function (k) {
                return k == a
            })) return h.currentTarget = a, h.type = b, c.call(a, h)
        } : function (h) {
            h = new Vf(h);
            h.currentTarget = a;
            return c.call(a, h)
        };
        g = ie(g);
        a.addEventListener ? ("mouseenter" == b && f ? b = "mouseover" : "mouseleave" == b && f ? b = "mouseout" : "mousewheel" == b && "MozBoxSizing" in document.documentElement.style && (b = "MozMousePixelScroll"), Zf() || "boolean" === typeof d ? a.addEventListener(b, g, d) : a.addEventListener(b, g, !!d.capture)) : a.attachEvent("on" + b, g);
        hb[e] = [a, b, c, g, d];
        return e
    }

    function $f(a) {
        a && ("string" == typeof a && (a = [a]), G(a, function (b) {
            if (b in hb) {
                var c = hb[b], d = c[0], e = c[1], f = c[3];
                c = c[4];
                d.removeEventListener ? Zf() || "boolean" === typeof c ? d.removeEventListener(e, f, c) : d.removeEventListener(e, f, !!c.capture) : d.detachEvent && d.detachEvent("on" + e, f);
                delete hb[b]
            }
        }))
    }

    var ag = window.ytcsi && window.ytcsi.now ? window.ytcsi.now : window.performance && window.performance.timing && window.performance.now && window.performance.timing.navigationStart ? function () {
        return window.performance.timing.navigationStart + window.performance.now()
    } : function () {
        return (new Date).getTime()
    };

    function bg(a) {
        this.w = a;
        this.f = null;
        this.j = 0;
        this.m = null;
        this.l = 0;
        this.h = [];
        for (a = 0; 4 > a; a++) this.h.push(0);
        this.i = 0;
        this.D = V(window, "mousemove", C(this.F, this));
        a = C(this.A, this);
        A(a) && (a = ie(a));
        this.K = window.setInterval(a, 25)
    }

    E(bg, N);
    bg.prototype.F = function (a) {
        void 0 === a.f && Wf(a);
        var b = a.f;
        void 0 === a.g && Wf(a);
        this.f = new Zb(b, a.g)
    };
    bg.prototype.A = function () {
        if (this.f) {
            var a = ag();
            if (0 != this.j) {
                var b = this.m, c = this.f, d = b.x - c.x;
                b = b.y - c.y;
                d = Math.sqrt(d * d + b * b) / (a - this.j);
                this.h[this.i] = .5 < Math.abs((d - this.l) / this.l) ? 1 : 0;
                for (c = b = 0; 4 > c; c++) b += this.h[c] || 0;
                3 <= b && this.w();
                this.l = d
            }
            this.j = a;
            this.m = this.f;
            this.i = (this.i + 1) % 4
        }
    };
    bg.prototype.o = function () {
        window.clearInterval(this.K);
        $f(this.D)
    };
    var cg = {};

    function dg(a) {
        var b = void 0 === a ? {} : a;
        a = void 0 === b.Ca ? !0 : b.Ca;
        b = void 0 === b.Oa ? !1 : b.Oa;
        if (null == z("_lact", window)) {
            var c = parseInt(Q("LACT"), 10);
            c = isFinite(c) ? D() - Math.max(c, 0) : -1;
            y("_lact", c, window);
            y("_fact", c, window);
            -1 == c && eg();
            V(document, "keydown", eg);
            V(document, "keyup", eg);
            V(document, "mousedown", eg);
            V(document, "mouseup", eg);
            a && (b ? V(window, "touchmove", function () {
                fg("touchmove", 200)
            }, {passive: !0}) : (V(window, "resize", function () {
                fg("resize", 200)
            }), V(window, "scroll", function () {
                fg("scroll", 200)
            })));
            new bg(function () {
                fg("mouse", 100)
            });
            V(document, "touchstart", eg, {passive: !0});
            V(document, "touchend", eg, {passive: !0})
        }
    }

    function fg(a, b) {
        cg[a] || (cg[a] = !0, Bf(function () {
            eg();
            cg[a] = !1
        }, b))
    }

    function eg() {
        null == z("_lact", window) && dg();
        var a = D();
        y("_lact", a, window);
        -1 == z("_fact", window) && y("_fact", a, window);
        (a = z("ytglobal.ytUtilActivityCallback_")) && a()
    }

    function gg() {
        var a = z("_lact", window);
        return null == a ? -1 : Math.max(D() - a, 0)
    }

    var hg = ve("initial_gel_batch_timeout", 1E3), ig = Math.pow(2, 16) - 1, jg = null, kg = 0,
        lg = {log_event: "events", log_interaction: "interactions"}, mg = Object.create(null);
    mg.log_event = "GENERIC_EVENT_LOGGING";
    mg.log_interaction = "INTERACTION_LOGGING";
    var ng = new Set(["log_event"]), og = {}, pg = 0, qg = 0, rg = 0, sg = !0,
        W = z("ytLoggingTransportLogPayloadsQueue_") || {};
    y("ytLoggingTransportLogPayloadsQueue_", W, void 0);
    var tg = z("ytLoggingTransportTokensToCttTargetIds_") || {};
    y("ytLoggingTransportTokensToCttTargetIds_", tg, void 0);
    var ug = z("ytLoggingTransportDispatchedStats_") || {};
    y("ytLoggingTransportDispatchedStats_", ug, void 0);
    y("ytytLoggingTransportCapturedTime_", z("ytLoggingTransportCapturedTime_") || {}, void 0);

    function vg() {
        T(pg);
        T(qg);
        qg = 0;
        if (!ib(W)) {
            for (var a in W) {
                var b = og[a];
                b && b.isReady() && (wg(a, b), delete W[a], sg = !1)
            }
            ib(W) || xg()
        }
    }

    function xg() {
        R("web_gel_timeout_cap") && !qg && (qg = S(vg, 6E4));
        T(pg);
        var a = Q("LOGGING_BATCH_TIMEOUT", ve("web_gel_debounce_ms", 1E4));
        R("shorten_initial_gel_batch_timeout") && sg && (a = hg);
        pg = S(vg, a)
    }

    function yg(a, b) {
        b = void 0 === b ? "" : b;
        W[a] = W[a] || {};
        W[a][b] = W[a][b] || [];
        return W[a][b]
    }

    function wg(a, b) {
        var c = lg[a], d = ug[a] || {};
        ug[a] = d;
        var e = Math.round(U());
        for (p in W[a]) {
            var f = mb, g = b.f || zg();
            g = {
                client: {
                    hl: g.Ga,
                    gl: g.Fa,
                    clientName: g.Ea,
                    clientVersion: g.innertubeContextClientVersion,
                    configInfo: g.Da
                }
            };
            var h = window.devicePixelRatio;
            h && 1 != h && (g.client.screenDensityFloat = String(h));
            h = Q("EXPERIMENTS_TOKEN", "");
            "" !== h && (g.client.experimentsToken = h);
            var k = h = void 0, l = [], m = Q("EXPERIMENTS_FORCED_FLAGS", {});
            for (k in m) l.push({key: k, value: String(m[k])});
            k = Q("EXPERIMENT_FLAGS", {});
            for (h in k) h.startsWith("force_") &&
            void 0 === m[h] && l.push({key: h, value: String(k[h])});
            h = l;
            0 < h.length && (g.request = {internalExperimentFlags: h});
            Q("DELEGATED_SESSION_ID") && !R("pageid_as_header_web") && (g.user = {onBehalfOfUser: Q("DELEGATED_SESSION_ID")});
            f = f({context: g});
            f[c] = yg(a, p);
            d.dispatchedEventCount = d.dispatchedEventCount || 0;
            d.dispatchedEventCount += f[c].length;
            if (g = tg[p]) a:{
                l = p;
                if (g.videoId) h = "VIDEO"; else if (g.playlistId) h = "PLAYLIST"; else break a;
                f.credentialTransferTokenTargetId = g;
                f.context = f.context || {};
                f.context.user = f.context.user ||
                    {};
                f.context.user.credentialTransferTokens = [{token: l, scope: h}]
            }
            delete tg[p];
            f.requestTimeMs = e;
            R("unsplit_gel_payloads_in_logs") && (f.unsplitGelPayloadsInLogs = !0);
            if (g = Q("EVENT_ID", void 0)) h = Q("BATCH_CLIENT_COUNTER", void 0) || 0, !h && R("web_client_counter_random_seed") && (h = Math.floor(Math.random() * ig / 2)), h++, h > ig && (h = 1), P("BATCH_CLIENT_COUNTER", h), g = {
                serializedEventId: g,
                clientCounter: h
            }, f.serializedClientEventId = g, jg && kg && R("log_gel_rtt_web") && (f.previousBatchInfo = {
                serializedClientEventId: jg,
                roundtripMs: kg
            }),
                jg = g, kg = 0;
            Ag(b, a, f, {retry: ng.has(a), onSuccess: C(Bg, this, U())})
        }
        if (d.previousDispatchMs) {
            c = e - d.previousDispatchMs;
            var p = d.diffCount || 0;
            d.averageTimeBetweenDispatchesMs = p ? (d.averageTimeBetweenDispatchesMs * p + c) / (p + 1) : c;
            d.diffCount = p + 1
        }
        d.previousDispatchMs = e
    }

    function Bg(a) {
        kg = Math.round(U() - a)
    }

    var Cg = z("ytLoggingGelSequenceIdObj_") || {};
    y("ytLoggingGelSequenceIdObj_", Cg, void 0);

    function Dg(a, b, c, d) {
        d = void 0 === d ? {} : d;
        var e = {};
        e.eventTimeMs = Math.round(d.timestamp || U());
        e[a] = b;
        e.context = {lastActivityMs: String(d.timestamp ? -1 : gg())};
        R("log_sequence_info_on_gel_web") && d.P && (a = e.context, b = d.P, Cg[b] = b in Cg ? Cg[b] + 1 : 0, a.sequence = {
            index: Cg[b],
            groupKey: b
        }, d.qb && delete Cg[d.P]);
        (d = d.da) ? (a = {}, d.videoId ? a.videoId = d.videoId : d.playlistId && (a.playlistId = d.playlistId), tg[d.token] = a, d = yg("log_event", d.token)) : d = yg("log_event");
        d.push(e);
        c && (og.log_event = new c);
        c = ve("web_logging_max_batch") ||
            100;
        e = U();
        d.length >= c ? vg() : 10 <= e - rg && (xg(), rg = e)
    }

    function Eg() {
        return "INNERTUBE_API_KEY" in ae && "INNERTUBE_API_VERSION" in ae
    }

    function zg() {
        return {
            innertubeApiKey: Q("INNERTUBE_API_KEY", void 0),
            innertubeApiVersion: Q("INNERTUBE_API_VERSION", void 0),
            Da: Q("INNERTUBE_CONTEXT_CLIENT_CONFIG_INFO"),
            Ea: Q("INNERTUBE_CONTEXT_CLIENT_NAME", "WEB"),
            innertubeContextClientVersion: Q("INNERTUBE_CONTEXT_CLIENT_VERSION", void 0),
            Ga: Q("INNERTUBE_CONTEXT_HL", void 0),
            Fa: Q("INNERTUBE_CONTEXT_GL", void 0),
            Ha: Q("INNERTUBE_HOST_OVERRIDE", void 0) || "",
            Ia: !!Q("INNERTUBE_USE_THIRD_PARTY_AUTH", !1)
        }
    }

    function Fg(a, b, c) {
        c = void 0 === c ? {} : c;
        var d = {"X-Goog-Visitor-Id": c.visitorData || Q("VISITOR_DATA", "")};
        if (b && b.includes("www.youtube-nocookie.com")) return d;
        (b = c.nb || Q("AUTHORIZATION")) || (a ? b = "Bearer " + z("gapi.auth.getToken")().mb : b = Bc([]));
        b && (d.Authorization = b, d["X-Goog-AuthUser"] = Q("SESSION_INDEX", 0), R("pageid_as_header_web") && (d["X-Goog-PageId"] = Q("DELEGATED_SESSION_ID")));
        return d
    }

    function Gg(a) {
        a = Object.assign({}, a);
        delete a.Authorization;
        var b = Bc();
        if (b) {
            var c = new yd;
            c.update(Q("INNERTUBE_API_KEY", void 0));
            c.update(b);
            b = c.digest();
            c = 3;
            Ba(b);
            void 0 === c && (c = 0);
            if (!Tb) {
                Tb = {};
                for (var d = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789".split(""), e = ["+/=", "+/", "-_=", "-_.", "-_"], f = 0; 5 > f; f++) {
                    var g = d.concat(e[f].split(""));
                    Sb[f] = g;
                    for (var h = 0; h < g.length; h++) {
                        var k = g[h];
                        void 0 === Tb[k] && (Tb[k] = h)
                    }
                }
            }
            c = Sb[c];
            d = [];
            for (e = 0; e < b.length; e += 3) {
                var l = b[e], m = (f = e + 1 < b.length) ?
                    b[e + 1] : 0;
                k = (g = e + 2 < b.length) ? b[e + 2] : 0;
                h = l >> 2;
                l = (l & 3) << 4 | m >> 4;
                m = (m & 15) << 2 | k >> 6;
                k &= 63;
                g || (k = 64, f || (m = 64));
                d.push(c[h], c[l], c[m] || "", c[k] || "")
            }
            a.hash = d.join("")
        }
        return a
    }

    function Hg(a, b, c, d) {
        Vb.set("" + a, b, {ma: c, path: "/", domain: void 0 === d ? "youtube.com" : d, secure: !1})
    }

    function Ig() {
        var a = new Td;
        (a = a.isAvailable() ? new Zd(a, "yt.innertube") : null) || (a = new Ud("yt.innertube"), a = a.isAvailable() ? a : null);
        this.f = a ? new Pd(a) : null;
        this.g = document.domain || window.location.hostname
    }

    Ig.prototype.set = function (a, b, c, d) {
        c = c || 31104E3;
        this.remove(a);
        if (this.f) try {
            this.f.set(a, b, D() + 1E3 * c);
            return
        } catch (f) {
        }
        var e = "";
        if (d) try {
            e = escape(Dd(b))
        } catch (f) {
            return
        } else e = escape(b);
        Hg(a, e, c, this.g)
    };
    Ig.prototype.get = function (a, b) {
        var c = void 0, d = !this.f;
        if (!d) try {
            c = this.f.get(a)
        } catch (e) {
            d = !0
        }
        if (d && (c = Vb.get("" + a, void 0)) && (c = unescape(c), b)) try {
            c = JSON.parse(c)
        } catch (e) {
            this.remove(a), c = void 0
        }
        return c
    };
    Ig.prototype.remove = function (a) {
        this.f && this.f.remove(a);
        var b = this.g;
        Vb.remove("" + a, "/", void 0 === b ? "youtube.com" : b)
    };
    var Jg = new Ig;

    function Kg(a, b, c, d) {
        if (d) return null;
        d = Jg.get("nextId", !0) || 1;
        var e = Jg.get("requests", !0) || {};
        e[d] = {method: a, request: b, authState: Gg(c), requestTime: Math.round(U())};
        Jg.set("nextId", d + 1, 86400, !0);
        Jg.set("requests", e, 86400, !0);
        return d
    }

    function Lg(a) {
        var b = Jg.get("requests", !0) || {};
        delete b[a];
        Jg.set("requests", b, 86400, !0)
    }

    function Mg(a) {
        var b = Jg.get("requests", !0);
        if (b) {
            for (var c in b) {
                var d = b[c];
                if (!(6E4 > Math.round(U()) - d.requestTime)) {
                    var e = d.authState, f = Gg(Fg(!1));
                    kb(e, f) && (e = d.request, "requestTimeMs" in e && (e.requestTimeMs = Math.round(U())), Ag(a, d.method, e, {}));
                    delete b[c]
                }
            }
            Jg.set("requests", b, 86400, !0)
        }
    }

    function Ng(a) {
        var b = this;
        this.f = null;
        a ? this.f = a : R("delay_gel_until_config_ready") ? Eg() && (this.f = zg()) : this.f = zg();
        Cf(function () {
            Mg(b)
        }, 0, 5E3)
    }

    Ng.prototype.isReady = function () {
        !this.f && Eg() && (this.f = zg());
        return !!this.f
    };

    function Ag(a, b, c, d) {
        !Q("VISITOR_DATA") && "visitor_id" !== b && .01 > Math.random() && ke(Error("Missing VISITOR_DATA when sending innertube request."));
        var e = {
            headers: {"Content-Type": "application/json"}, method: "POST", C: c, ra: "JSON", O: function () {
                d.O()
            },
            qa: d.O, onSuccess: function (v, r) {
                if (d.onSuccess) d.onSuccess(r)
            },
            oa: function (v) {
                if (d.onSuccess) d.onSuccess(v)
            },
            onError: function (v, r) {
                if (d.onError) d.onError(r)
            },
            tb: function (v) {
                if (d.onError) d.onError(v)
            },
            timeout: d.timeout, withCredentials: !0
        }, f = "", g = a.f.Ha;
        g && (f = g);
        g = a.f.Ia || !1;
        var h = Fg(g, f, d);
        Object.assign(e.headers, h);
        e.headers.Authorization && !f && (e.headers["x-origin"] = window.location.origin);
        var k = oe("" + f + ("/youtubei/" + a.f.innertubeApiVersion + "/" + b), {
            alt: "json",
            key: a.f.innertubeApiKey
        }), l;
        if (d.retry && R("retry_web_logging_batches") && "www.youtube-nocookie.com" != f && (l = Kg(b, c, h, g))) {
            var m = e.onSuccess, p = e.oa;
            e.onSuccess = function (v, r) {
                Lg(l);
                m(v, r)
            };
            c.oa = function (v, r) {
                Lg(l);
                p(v, r)
            }
        }
        try {
            R("use_fetch_for_op_xhr") ? Fe(k, e) : (e.method = "POST", e.C || (e.C = {}), Ie(k, e))
        } catch (v) {
            if ("InvalidAccessError" == v) l && (Lg(l), l = 0), ke(Error("An extension is blocking network request."));
            else throw v;
        }
        l && Cf(function () {
            Mg(a)
        }, 0, 5E3)
    }

    var Og = D().toString();

    function Pg() {
        a:{
            if (window.crypto && window.crypto.getRandomValues) try {
                var a = Array(16), b = new Uint8Array(16);
                window.crypto.getRandomValues(b);
                for (var c = 0; c < a.length; c++) a[c] = b[c];
                var d = a;
                break a
            } catch (e) {
            }
            d = Array(16);
            for (a = 0; 16 > a; a++) {
                b = D();
                for (c = 0; c < b % 23; c++) d[a] = Math.random();
                d[a] = Math.floor(256 * Math.random())
            }
            if (Og) for (a = 1, b = 0; b < Og.length; b++) d[a % 16] = d[a % 16] ^ d[(a - 1) % 16] / 4 ^ Og.charCodeAt(b), a++
        }
        a = [];
        for (b = 0; b < d.length; b++) a.push("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789-_".charAt(d[b] & 63));
        return a.join("")
    }

    var Qg = z("ytLoggingTimeDocumentNonce_") || Pg();
    y("ytLoggingTimeDocumentNonce_", Qg, void 0);

    function Rg(a) {
        this.f = a
    }

    function Sg(a) {
        var b = {};
        void 0 !== a.f.trackingParams ? b.trackingParams = a.f.trackingParams : (b.veType = a.f.veType, null != a.f.veCounter && (b.veCounter = a.f.veCounter), null != a.f.elementIndex && (b.elementIndex = a.f.elementIndex));
        void 0 !== a.f.dataElement && (b.dataElement = Sg(a.f.dataElement));
        void 0 !== a.f.youtubeData && (b.youtubeData = a.f.youtubeData);
        return b
    }

    Rg.prototype.toString = function () {
        return JSON.stringify(Sg(this))
    };
    var Tg = 1;

    function Ug(a) {
        a = void 0 === a ? 0 : a;
        return 0 == a ? "client-screen-nonce" : "client-screen-nonce." + a
    }

    function Vg(a) {
        a = void 0 === a ? 0 : a;
        return 0 == a ? "ROOT_VE_TYPE" : "ROOT_VE_TYPE." + a
    }

    function Wg(a) {
        return Q(Vg(void 0 === a ? 0 : a), void 0)
    }

    y("yt_logging_screen.getRootVeType", Wg, void 0);

    function Xg() {
        var a = Wg(0), b;
        a ? b = new Rg({veType: a, youtubeData: void 0}) : b = null;
        return b
    }

    function Yg() {
        var a = Q("csn-to-ctt-auth-info");
        a || (a = {}, P("csn-to-ctt-auth-info", a));
        return a
    }

    function Zg(a) {
        a = void 0 === a ? 0 : a;
        var b = Q(Ug(a));
        b || 0 != a || (R("kevlar_client_side_screens") || R("c3_client_side_screens") ? b = "UNDEFINED_CSN" : b = Q("EVENT_ID"));
        return b ? b : null
    }

    y("yt_logging_screen.getCurrentCsn", Zg, void 0);

    function $g(a, b, c) {
        var d = Yg();
        (c = Zg(c)) && delete d[c];
        b && (d[a] = b)
    }

    function ah(a) {
        return Yg()[a]
    }

    y("yt_logging_screen.getCttAuthInfo", ah, void 0);

    function bh(a, b, c, d) {
        c = void 0 === c ? 0 : c;
        if (a !== Q(Ug(c)) || b !== Q(Vg(c))) $g(a, d, c), P(Ug(c), a), P(Vg(c), b), 0 == c && (b = function () {
            setTimeout(function () {
                a && Dg("foregroundHeartbeatScreenAssociated", {clientDocumentNonce: Qg, clientScreenNonce: a}, Ng)
            }, 0)
        }, "requestAnimationFrame" in window ? window.requestAnimationFrame(b) : b())
    }

    y("yt_logging_screen.setCurrentScreen", bh, void 0);

    function ch(a, b, c) {
        b = void 0 === b ? {} : b;
        c = void 0 === c ? !1 : c;
        var d = Q("EVENT_ID");
        d && (b.ei || (b.ei = d));
        if (b) {
            d = a;
            var e = void 0 === e ? !0 : e;
            var f = Q("VALID_SESSION_TEMPDATA_DOMAINS", []), g = J(L(3, window.location.href));
            g && f.push(g);
            g = J(L(3, d));
            if (0 <= La(f, g) || !g && 0 == d.lastIndexOf("/", 0)) if (R("autoescape_tempdata_url") && (f = document.createElement("a"), Kb(f, d), d = f.href), d) {
                g = d.match(kc);
                d = g[5];
                f = g[6];
                g = g[7];
                var h = "";
                d && (h += d);
                f && (h += "?" + f);
                g && (h += "#" + g);
                d = h;
                f = d.indexOf("#");
                if (d = 0 > f ? d : d.substr(0, f)) if (e && !b.csn && (b.itct ||
                    b.ved) && (b = Object.assign({csn: Zg()}, b)), k) {
                    var k = parseInt(k, 10);
                    isFinite(k) && 0 < k && (e = b, b = "ST-" + Nb(d).toString(36), e = e ? mc(e) : "", Hg(b, e, k || 5))
                } else k = b, e = "ST-" + Nb(d).toString(36), k = k ? mc(k) : "", Hg(e, k, 5)
            }
        }
        if (c) return !1;
        if ((window.ytspf || {}).enabled) spf.navigate(a); else {
            var l = void 0 === l ? {} : l;
            var m = void 0 === m ? "" : m;
            var p = void 0 === p ? window : p;
            c = p.location;
            a = nc(a, l) + m;
            a = a instanceof H ? a : Db(a);
            c.href = Bb(a)
        }
        return !0
    }

    function dh(a, b) {
        this.version = a;
        this.args = b
    }

    function eh(a, b) {
        this.topic = a;
        this.f = b
    }

    eh.prototype.toString = function () {
        return this.topic
    };
    var fh = z("ytPubsub2Pubsub2Instance") || new O;
    O.prototype.subscribe = O.prototype.subscribe;
    O.prototype.unsubscribeByKey = O.prototype.N;
    O.prototype.publish = O.prototype.M;
    O.prototype.clear = O.prototype.clear;
    y("ytPubsub2Pubsub2Instance", fh, void 0);
    var gh = z("ytPubsub2Pubsub2SubscribedKeys") || {};
    y("ytPubsub2Pubsub2SubscribedKeys", gh, void 0);
    var hh = z("ytPubsub2Pubsub2TopicToKeys") || {};
    y("ytPubsub2Pubsub2TopicToKeys", hh, void 0);
    var ih = z("ytPubsub2Pubsub2IsAsync") || {};
    y("ytPubsub2Pubsub2IsAsync", ih, void 0);
    y("ytPubsub2Pubsub2SkipSubKey", null, void 0);

    function jh(a, b) {
        var c = kh();
        c && c.publish.call(c, a.toString(), a, b)
    }

    function lh(a) {
        var b = mh, c = kh();
        if (!c) return 0;
        var d = c.subscribe(b.toString(), function (e, f) {
            var g = z("ytPubsub2Pubsub2SkipSubKey");
            g && g == d || (g = function () {
                if (gh[d]) try {
                    if (f && b instanceof eh && b != e) try {
                        var h = b.f, k = f;
                        if (!k.args || !k.version) throw Error("yt.pubsub2.Data.deserialize(): serializedData is incomplete.");
                        try {
                            if (!h.H) {
                                var l = new h;
                                h.H = l.version
                            }
                            var m = h.H
                        } catch (p) {
                        }
                        if (!m || k.version != m) throw Error("yt.pubsub2.Data.deserialize(): serializedData version is incompatible.");
                        try {
                            f = Reflect.construct(h,
                                Ra(k.args))
                        } catch (p) {
                            throw p.message = "yt.pubsub2.Data.deserialize(): " + p.message, p;
                        }
                    } catch (p) {
                        throw p.message = "yt.pubsub2.pubsub2 cross-binary conversion error for " + b.toString() + ": " + p.message, p;
                    }
                    a.call(window, f)
                } catch (p) {
                    je(p)
                }
            }, ih[b.toString()] ? z("yt.scheduler.instance") ? Bf(g) : S(g, 0) : g())
        });
        gh[d] = !0;
        hh[b.toString()] || (hh[b.toString()] = []);
        hh[b.toString()].push(d);
        return d
    }

    function nh() {
        var a = oh, b = lh(function (c) {
            a.apply(void 0, arguments);
            ph(b)
        });
        return b
    }

    function ph(a) {
        var b = kh();
        b && ("number" === typeof a && (a = [a]), G(a, function (c) {
            b.unsubscribeByKey(c);
            delete gh[c]
        }))
    }

    function kh() {
        return z("ytPubsub2Pubsub2Instance")
    }

    function qh(a) {
        dh.call(this, 1, arguments);
        this.csn = a
    }

    t(qh, dh);
    var mh = new eh("screen-created", qh), rh = [], sh = 0;

    function th(a, b, c) {
        b = {
            csn: a, parentVe: Sg(b), childVes: Na(c, function (e) {
                return Sg(e)
            })
        };
        c = q(c);
        for (var d = c.next(); !d.done; d = c.next()) d = Sg(d.value), (ib(d) || !d.trackingParams && !d.veType) && $e(Error("Child VE logged with no data"), "WARNING");
        c = {da: ah(a), P: a};
        "UNDEFINED_CSN" == a ? uh("visualElementAttached", b, c) : Dg("visualElementAttached", b, Ng, c)
    }

    function uh(a, b, c) {
        rh.push({Na: a, payload: b, options: c});
        sh || (sh = nh())
    }

    function oh(a) {
        if (rh) {
            for (var b = q(rh), c = b.next(); !c.done; c = b.next()) c = c.value, c.payload && (c.payload.csn = a.csn, Dg(c.Na, c.payload, null, c.options));
            rh.length = 0
        }
        sh = 0
    }

    function vh(a) {
        a = a || {};
        var b = {}, c = {};
        this.url = a.url || "";
        this.args = a.args || lb(b);
        this.assets = a.assets || {};
        this.attrs = a.attrs || lb(c);
        this.fallback = a.fallback || null;
        this.fallbackMessage = a.fallbackMessage || null;
        this.html5 = !!a.html5;
        this.disable = a.disable || {};
        this.loaded = !!a.loaded;
        this.messages = a.messages || {}
    }

    vh.prototype.clone = function () {
        var a = new vh, b;
        for (b in this) if (this.hasOwnProperty(b)) {
            var c = this[b];
            "object" == za(c) ? a[b] = lb(c) : a[b] = c
        }
        return a
    };

    function wh() {
        N.call(this);
        this.f = []
    }

    t(wh, N);
    wh.prototype.o = function () {
        for (; this.f.length;) {
            var a = this.f.pop();
            a.target.removeEventListener(a.name, a.ob)
        }
        N.prototype.o.call(this)
    };
    var xh = /cssbin\/(?:debug-)?([a-zA-Z0-9_-]+?)(?:-2x|-web|-rtl|-vfl|.css)/;

    function yh(a) {
        a = a || "";
        if (window.spf) {
            var b = a.match(xh);
            spf.style.load(a, b ? b[1] : "", void 0)
        } else zh(a)
    }

    function zh(a) {
        var b = Ah(a), c = document.getElementById(b), d = c && ef(c, "loaded");
        d || c && !d || (c = Bh(a, b, function () {
            ef(c, "loaded") || (cf(c), of(b), S(Ja(pf, b), 0))
        }))
    }

    function Bh(a, b, c) {
        var d = document.createElement("link");
        d.id = b;
        d.onload = function () {
            c && setTimeout(c, 0)
        };
        a = ic(a);
        d.rel = "stylesheet";
        d.href = yb(a).toString();
        (document.getElementsByTagName("head")[0] || document.body).appendChild(d);
        return d
    }

    function Ah(a) {
        var b = ec(document, "A");
        Kb(b, new H(zb, a));
        a = b.href.replace(/^[a-zA-Z]+:\/\//, "//");
        return "css-" + Nb(a)
    }

    function Ch(a, b, c, d) {
        N.call(this);
        var e = this;
        this.m = this.X = a;
        this.U = b;
        this.w = !1;
        this.api = {};
        this.V = this.D = null;
        this.F = new O;
        Ad(this, Ja(Bd, this.F));
        this.j = {};
        this.R = this.W = this.h = this.ca = this.f = null;
        this.K = !1;
        this.l = this.A = null;
        this.Y = {};
        this.ua = ["onReady"];
        this.ba = null;
        this.ka = NaN;
        this.S = {};
        this.i = d;
        Dh(this);
        this.Z("WATCH_LATER_VIDEO_ADDED", this.Ka.bind(this));
        this.Z("WATCH_LATER_VIDEO_REMOVED", this.La.bind(this));
        this.Z("onAdAnnounce", this.wa.bind(this));
        this.va = new wh(this);
        Ad(this, Ja(Bd, this.va));
        this.T = 0;
        c ? this.T = S(function () {
            e.loadNewVideoConfig(c)
        }, 0) : d && (Eh(this), Fh(this))
    }

    t(Ch, N);
    n = Ch.prototype;
    n.getId = function () {
        return this.U
    };
    n.loadNewVideoConfig = function (a) {
        if (!this.g) {
            this.T && (T(this.T), this.T = 0);
            a instanceof vh || (a = new vh(a));
            this.ca = a;
            this.f = a.clone();
            Eh(this);
            this.W || (this.W = Gh(this, this.f.args.jsapicallback || "onYouTubePlayerReady"));
            this.f.args.jsapicallback = null;
            if (a = this.f.attrs.width) this.m.style.width = vc(Number(a) || a);
            if (a = this.f.attrs.height) this.m.style.height = vc(Number(a) || a);
            Fh(this);
            this.w && Hh(this)
        }
    };

    function Eh(a) {
        var b;
        a.i ? b = a.i.rootElementId : b = a.f.attrs.id;
        a.h = b || a.h;
        "video-player" == a.h && (a.h = a.U, a.f.attrs.id = a.U);
        a.m.id == a.h && (a.h += "-player", a.f.attrs.id = a.h)
    }

    n.za = function () {
        return this.ca
    };

    function Hh(a) {
        a.f && !a.f.loaded && (a.f.loaded = !0, "0" != a.f.args.autoplay ? a.api.loadVideoByPlayerVars(a.f.args) : a.api.cueVideoByPlayerVars(a.f.args))
    }

    function Ih(a) {
        var b = !0, c = Jh(a);
        c && a.f && (a = Kh(a), b = ef(c, "version") === a);
        return b && !!z("yt.player.Application.create")
    }

    function Fh(a) {
        if (!a.g && !a.K) {
            var b = Ih(a);
            if (b && "html5" == (Jh(a) ? "html5" : null)) a.R = "html5", a.w || Lh(a); else if (Mh(a), a.R = "html5", b && a.l) a.X.appendChild(a.l), Lh(a); else {
                a.f && (a.f.loaded = !0);
                var c = !1;
                a.A = function () {
                    c = !0;
                    if (a.i) var d = a.i.serializedExperimentFlags; else a.f && a.f.args && (d = a.f.args.fflags);
                    d = "true" == le(d || "").player_bootstrap_method ? z("yt.player.Application.createAlternate") || z("yt.player.Application.create") : z("yt.player.Application.create");
                    var e = a.f ? a.f.clone() : void 0;
                    d(a.X, e, a.i);
                    Lh(a)
                };
                a.K = !0;
                b ? a.A() : (tf(Kh(a), a.A), yh(a.i ? a.i.cssUrl : a.f.assets.css), Nh(a) && !c && y("yt.player.Application.create", null, void 0))
            }
        }
    }

    function Jh(a) {
        var b = ac(a.h);
        !b && a.m && a.m.querySelector && (b = a.m.querySelector("#" + a.h));
        return b
    }

    function Lh(a) {
        if (!a.g) {
            var b = Jh(a), c = !1;
            b && b.getApiInterface && b.getApiInterface() && (c = !0);
            c ? (a.K = !1, b.isNotServable && a.f && b.isNotServable(a.f.args.video_id) || Oh(a)) : a.ka = S(function () {
                Lh(a)
            }, 50)
        }
    }

    function Oh(a) {
        Dh(a);
        a.w = !0;
        var b = Jh(a);
        b.addEventListener && (a.D = Ph(a, b, "addEventListener"));
        b.removeEventListener && (a.V = Ph(a, b, "removeEventListener"));
        var c = b.getApiInterface();
        c = c.concat(b.getInternalApiInterface());
        for (var d = 0; d < c.length; d++) {
            var e = c[d];
            a.api[e] || (a.api[e] = Ph(a, b, e))
        }
        for (var f in a.j) a.D(f, a.j[f]);
        Hh(a);
        a.W && a.W(a.api);
        a.F.M("onReady", a.api)
    }

    function Ph(a, b, c) {
        var d = b[c];
        return function () {
            try {
                return a.ba = null, d.apply(b, arguments)
            } catch (e) {
                "sendAbandonmentPing" != c && (e.params = c, a.ba = e, ke(e))
            }
        }
    }

    function Dh(a) {
        a.w = !1;
        if (a.V) for (var b in a.j) a.V(b, a.j[b]);
        for (var c in a.S) T(parseInt(c, 10));
        a.S = {};
        a.D = null;
        a.V = null;
        for (var d in a.api) a.api[d] = null;
        a.api.addEventListener = a.Z.bind(a);
        a.api.removeEventListener = a.Qa.bind(a);
        a.api.destroy = a.dispose.bind(a);
        a.api.getLastError = a.Aa.bind(a);
        a.api.getPlayerType = a.Ba.bind(a);
        a.api.getCurrentVideoConfig = a.za.bind(a);
        a.api.loadNewVideoConfig = a.loadNewVideoConfig.bind(a);
        a.api.isReady = a.Ja.bind(a)
    }

    n.Ja = function () {
        return this.w
    };
    n.Z = function (a, b) {
        var c = this, d = Gh(this, b);
        if (d) {
            if (!(0 <= La(this.ua, a) || this.j[a])) {
                var e = Qh(this, a);
                this.D && this.D(a, e)
            }
            this.F.subscribe(a, d);
            "onReady" == a && this.w && S(function () {
                d(c.api)
            }, 0)
        }
    };
    n.Qa = function (a, b) {
        if (!this.g) {
            var c = Gh(this, b);
            c && Jd(this.F, a, c)
        }
    };

    function Gh(a, b) {
        var c = b;
        if ("string" == typeof b) {
            if (a.Y[b]) return a.Y[b];
            c = function () {
                var d = z(b);
                d && d.apply(x, arguments)
            };
            a.Y[b] = c
        }
        return c ? c : null
    }

    function Qh(a, b) {
        var c = "ytPlayer" + b + a.U;
        a.j[b] = c;
        x[c] = function (d) {
            var e = S(function () {
                if (!a.g) {
                    a.F.M(b, d);
                    var f = a.S, g = String(e);
                    g in f && delete f[g]
                }
            }, 0);
            jb(a.S, String(e))
        };
        return c
    }

    n.wa = function (a) {
        of("a11y-announce", a)
    };
    n.Ka = function (a) {
        of("WATCH_LATER_VIDEO_ADDED", a)
    };
    n.La = function (a) {
        of("WATCH_LATER_VIDEO_REMOVED", a)
    };
    n.Ba = function () {
        return this.R || (Jh(this) ? "html5" : null)
    };
    n.Aa = function () {
        return this.ba
    };

    function Mh(a) {
        a.cancel();
        Dh(a);
        a.R = null;
        a.f && (a.f.loaded = !1);
        var b = Jh(a);
        b && (Ih(a) || !Nh(a) ? a.l = b : (b && b.destroy && b.destroy(), a.l = null));
        for (a = a.X; b = a.firstChild;) a.removeChild(b)
    }

    n.cancel = function () {
        this.A && zf(Kh(this), this.A);
        T(this.ka);
        this.K = !1
    };
    n.o = function () {
        Mh(this);
        if (this.l && this.f && this.l.destroy) try {
            this.l.destroy()
        } catch (b) {
            je(b)
        }
        this.Y = null;
        for (var a in this.j) x[this.j[a]] = null;
        this.ca = this.f = this.api = null;
        delete this.X;
        delete this.m;
        N.prototype.o.call(this)
    };

    function Nh(a) {
        return a.f && a.f.args && a.f.args.fflags ? -1 != a.f.args.fflags.indexOf("player_destroy_old_version=true") : !1
    }

    function Kh(a) {
        return a.i ? a.i.jsUrl : a.f.assets.js
    }

    var Rh = {}, Sh = "player_uid_" + (1E9 * Math.random() >>> 0);

    function Th(a) {
        delete Rh[a.getId()]
    }

    var Uh = window, X = Uh.performance || Uh.mozPerformance || Uh.msPerformance || Uh.webkitPerformance || {};

    function Vh(a, b) {
        dh.call(this, 1, arguments)
    }

    t(Vh, dh);

    function Wh(a, b) {
        dh.call(this, 1, arguments)
    }

    t(Wh, dh);
    var Xh = new eh("aft-recorded", Vh), Yh = new eh("timing-sent", Wh);
    var Zh = !1;

    function $h() {
        var a = ai(void 0);
        if (a.aft) return a.aft;
        for (var b = Q("TIMING_AFT_KEYS", ["ol"]), c = b.length, d = 0; d < c; d++) {
            var e = a[b[d]];
            if (e) return e
        }
        return NaN
    }

    function bi(a) {
        var b;
        (b = z("ytcsi." + (a || "") + "data_")) || (b = {tick: {}, info: {}}, Ka("ytcsi." + (a || "") + "data_", b));
        return b
    }

    function ci(a) {
        a = bi(a);
        a.info || (a.info = {});
        return a.info
    }

    function ai(a) {
        a = bi(a);
        a.tick || (a.tick = {});
        return a.tick
    }

    function di(a) {
        var b = bi(a).nonce;
        b || (b = Pg(), bi(a).nonce = b);
        return b
    }

    function ei() {
        var a = ai(""), b = $h();
        b && !Zh && (jh(Xh, new Vh(Math.round(b - a._start), void 0)), Zh = !0)
    }

    function fi(a, b) {
        for (var c = [], d = 1; d < arguments.length; ++d) c[d - 1] = arguments[d];
        if (!gi(a) || c.some(function (e) {
            return !gi(e)
        })) throw Error("Only objects may be merged.");
        c = q(c);
        for (d = c.next(); !d.done; d = c.next()) hi(a, d.value);
        return a
    }

    function hi(a, b) {
        for (var c in b) if (gi(b[c])) {
            if (c in a && !gi(a[c])) throw Error("Cannot merge an object into a non-object.");
            c in a || (a[c] = {});
            hi(a[c], b[c])
        } else if (ii(b[c])) {
            if (c in a && !ii(a[c])) throw Error("Cannot merge an array into a non-array.");
            c in a || (a[c] = []);
            ji(a[c], b[c])
        } else a[c] = b[c];
        return a
    }

    function ji(a, b) {
        for (var c = q(b), d = c.next(); !d.done; d = c.next()) d = d.value, gi(d) ? a.push(hi({}, d)) : ii(d) ? a.push(ji([], d)) : a.push(d);
        return a
    }

    function gi(a) {
        return "object" === typeof a && !Array.isArray(a)
    }

    function ii(a) {
        return "object" === typeof a && Array.isArray(a)
    }

    function ki() {
        var a = z("ytcsi.debug");
        a || (a = [], y("ytcsi.debug", a, void 0), y("ytcsi.reference", {}, void 0));
        return a
    }

    function li(a) {
        a = a || "";
        var b = z("ytcsi.reference");
        b || (ki(), b = z("ytcsi.reference"));
        if (b[a]) return b[a];
        var c = ki(), d = {timerName: a, info: {}, tick: {}};
        c.push(d);
        return b[a] = d
    }

    function mi(a, b, c) {
        c = void 0 === c ? {} : c;
        var d = Ng;
        Q("ytLoggingEventsDefaultDisabled", !1) && Ng == Ng && (d = null);
        Dg(a, b, d, c)
    }

    var ni = z("ytLoggingLatencyUsageStats_") || {};
    y("ytLoggingLatencyUsageStats_", ni, void 0);

    function oi() {
        this.f = 0
    }

    function pi() {
        oi.f || (oi.f = new oi);
        return oi.f
    }

    oi.prototype.tick = function (a, b, c) {
        qi(this, "tick_" + a + "_" + b) || mi("latencyActionTicked", {
            tickName: a,
            clientActionNonce: b
        }, {timestamp: c})
    };
    oi.prototype.info = function (a, b) {
        var c = Object.keys(a).join("");
        qi(this, "info_" + c + "_" + b) || (a.clientActionNonce = b, mi("latencyActionInfo", a))
    };

    function qi(a, b) {
        ni[b] = ni[b] || {count: 0};
        var c = ni[b];
        c.count++;
        c.time = U();
        a.f || (a.f = Cf(function () {
            var d = U(), e;
            for (e in ni) ni[e] && 6E4 < d - ni[e].time && delete ni[e];
            a && (a.f = 0)
        }, 0, 5E3));
        return 5 < c.count ? (6 === c.count && 1 > 1E5 * Math.random() && (c = new Ue("CSI data exceeded logging limit with key"), c.params = {key: b}, 0 === b.indexOf("info") ? Ye(c) : Xe(c)), !0) : !1
    }

    var Y = {},
        ri = (Y.ad_allowed = "adTypesAllowed", Y.yt_abt = "adBreakType", Y.ad_cpn = "adClientPlaybackNonce", Y.ad_docid = "adVideoId", Y.yt_ad_an = "adNetworks", Y.ad_at = "adType", Y.browse_id = "browseId", Y.p = "httpProtocol", Y.t = "transportProtocol", Y.cpn = "clientPlaybackNonce", Y.cseg = "creatorInfo.creatorSegment", Y.csn = "clientScreenNonce", Y.docid = "videoId", Y.GetHome_rid = "getHomeRequestId", Y.GetSearch_rid = "getSearchRequestId", Y.GetPlayer_rid = "getPlayerRequestId", Y.GetWatchNext_rid = "getWatchNextRequestId", Y.GetBrowse_rid =
            "getBrowseRequestId", Y.is_continuation = "isContinuation", Y.is_nav = "isNavigation", Y.b_p = "kabukiInfo.browseParams", Y.is_prefetch = "kabukiInfo.isPrefetch", Y.is_secondary_nav = "kabukiInfo.isSecondaryNav", Y.prev_browse_id = "kabukiInfo.prevBrowseId", Y.query_source = "kabukiInfo.querySource", Y.voz_type = "kabukiInfo.vozType", Y.yt_lt = "loadType", Y.mver = "creatorInfo.measurementVersion", Y.yt_ad = "isMonetized", Y.nr = "webInfo.navigationReason", Y.nrsu = "navigationRequestedSameUrl", Y.ncnp = "webInfo.nonPreloadedNodeCount", Y.prt =
            "playbackRequiresTap", Y.plt = "playerInfo.playbackType", Y.pis = "playerInfo.playerInitializedState", Y.paused = "playerInfo.isPausedOnLoad", Y.yt_pt = "playerType", Y.fmt = "playerInfo.itag", Y.yt_pl = "watchInfo.isPlaylist", Y.yt_pre = "playerInfo.preloadType", Y.yt_ad_pr = "prerollAllowed", Y.pa = "previousAction", Y.yt_red = "isRedSubscriber", Y.rce = "mwebInfo.responseContentEncoding", Y.scrh = "screenHeight", Y.scrw = "screenWidth", Y.st = "serverTimeMs", Y.aq = "tvInfo.appQuality", Y.br_trs = "tvInfo.bedrockTriggerState", Y.kebqat = "kabukiInfo.earlyBrowseRequestInfo.abandonmentType",
            Y.kebqa = "kabukiInfo.earlyBrowseRequestInfo.adopted", Y.label = "tvInfo.label", Y.is_mdx = "tvInfo.isMdx", Y.preloaded = "tvInfo.isPreloaded", Y.upg_player_vis = "playerInfo.visibilityState", Y.query = "unpluggedInfo.query", Y.upg_chip_ids_string = "unpluggedInfo.upgChipIdsString", Y.yt_vst = "videoStreamType", Y.vph = "viewportHeight", Y.vpw = "viewportWidth", Y.yt_vis = "isVisible", Y.rcl = "mwebInfo.responseContentLength", Y.GetSettings_rid = "mwebInfo.getSettingsRequestId", Y.GetTrending_rid = "mwebInfo.getTrendingRequestId", Y),
        si =
            "isContinuation isNavigation kabukiInfo.earlyBrowseRequestInfo.adopted kabukiInfo.isPrefetch kabukiInfo.isSecondaryNav isMonetized playerInfo.isPausedOnLoad prerollAllowed isRedSubscriber tvInfo.isMdx tvInfo.isPreloaded isVisible watchInfo.isPlaylist playbackRequiresTap".split(" "),
        ti = {},
        ui = (ti.mver = "MEASUREMENT_VERSION_", ti.pis = "PLAYER_INITIALIZED_STATE_", ti.yt_pt = "LATENCY_PLAYER_", ti.pa = "LATENCY_ACTION_", ti.yt_vst = "VIDEO_STREAM_TYPE_", ti),
        vi = "all_vc ap c cver cbrand cmodel cplatform ctheme ei l_an l_mm plid srt yt_fss yt_li vpst vpni2 vpil2 icrc icrt pa GetAccountOverview_rid GetHistory_rid cmt d_vpct d_vpnfi d_vpni nsru pc pfa pfeh pftr pnc prerender psc rc start tcrt tcrc ssr vpr vps yt_abt yt_fn yt_fs yt_pft yt_pre yt_pt yt_pvis ytu_pvis yt_ref yt_sts tds".split(" ");

    function wi(a) {
        return !!Q("FORCE_CSI_ON_GEL", !1) || R("csi_on_gel") || !!bi(a).useGel
    }

    function xi(a) {
        a = bi(a);
        if (!("gel" in a)) a.gel = {gelTicks: {}, gelInfos: {}}; else if (a.gel) {
            var b = a.gel;
            b.gelInfos || (b.gelInfos = {});
            b.gelTicks || (b.gelTicks = {})
        }
        return a.gel
    }

    function yi(a, b, c) {
        if (null !== b) if (ci(c)[a] = b, wi(c)) {
            var d = xi(c);
            if (d.gelInfos) d.gelInfos["info_" + a] = !0; else {
                var e = {};
                d.gelInfos = (e["info_" + a] = !0, e)
            }
            if (a in ri) {
                d = ri[a];
                0 <= La(si, d) && (b = !!b);
                a in ui && "string" === typeof b && (b = ui[a] + b.toUpperCase());
                a = d.split(".");
                e = d = {};
                for (var f = 0; f < a.length - 1; f++) {
                    var g = a[f];
                    e[g] = {};
                    e = e[g]
                }
                e[a[a.length - 1]] = b;
                b = fi({}, d)
            } else 0 <= La(vi, a) || (b = new Ue("Unknown label logged with GEL CSI"), b.params = {label: a}, ke(b)), b = void 0;
            b && wi(c) && (a = li(c || ""), fi(a.info, b), a = xi(c), "gelInfos" in
            a || (a.gelInfos = {}), fi(a.gelInfos, b), c = di(c), pi().info(b, c))
        } else li(c || "").info[a] = b
    }

    if (R("overwrite_polyfill_on_logging_lib_loaded")) {
        var zi = window;
        zi.ytcsi && (zi.ytcsi.info = yi)
    }
    C(X.clearResourceTimings || X.webkitClearResourceTimings || X.mozClearResourceTimings || X.msClearResourceTimings || X.oClearResourceTimings || xa, X);

    function Ai(a) {
        return (0 == a.search("cue") || 0 == a.search("load")) && "loadModule" != a
    }

    function Bi(a, b, c) {
        "string" === typeof a && (a = {mediaContentUrl: a, startSeconds: b, suggestedQuality: c});
        b = /\/([ve]|embed)\/([^#?]+)/.exec(a.mediaContentUrl);
        a.videoId = b && b[2] ? b[2] : null;
        return Ci(a)
    }

    function Ci(a, b, c) {
        if (Ca(a)) {
            b = ["endSeconds", "startSeconds", "mediaContentUrl", "suggestedQuality", "videoId"];
            c = {};
            for (var d = 0; d < b.length; d++) {
                var e = b[d];
                a[e] && (c[e] = a[e])
            }
            return c
        }
        return {videoId: a, startSeconds: b, suggestedQuality: c}
    }

    function Di(a, b, c, d) {
        if (Ca(a) && !Aa(a)) {
            b = "playlist list listType index startSeconds suggestedQuality".split(" ");
            c = {};
            for (d = 0; d < b.length; d++) {
                var e = b[d];
                a[e] && (c[e] = a[e])
            }
            return c
        }
        b = {index: b, startSeconds: c, suggestedQuality: d};
        "string" === typeof a && 16 == a.length ? b.list = "PL" + a : b.playlist = a;
        return b
    }

    function Ei(a) {
        a = void 0 === a ? !1 : a;
        N.call(this);
        this.f = new O(a);
        Ad(this, Ja(Bd, this.f))
    }

    E(Ei, N);
    Ei.prototype.subscribe = function (a, b, c) {
        return this.g ? 0 : this.f.subscribe(a, b, c)
    };
    Ei.prototype.j = function (a, b) {
        this.g || this.f.M.apply(this.f, arguments)
    };

    function Fi(a, b, c) {
        Ei.call(this);
        this.h = a;
        this.i = b;
        this.l = c
    }

    t(Fi, Ei);

    function Gi(a, b, c) {
        if (!a.g) {
            var d = a.h;
            d.g || a.i != d.f || (a = {id: a.l, command: b}, c && (a.data = c), d.f.postMessage(Dd(a), d.i))
        }
    }

    Fi.prototype.o = function () {
        this.i = this.h = null;
        Ei.prototype.o.call(this)
    };

    function Hi(a) {
        N.call(this);
        this.f = a;
        this.f.subscribe("command", this.sa, this);
        this.h = {};
        this.j = !1
    }

    t(Hi, N);
    n = Hi.prototype;
    n.start = function () {
        this.j || this.g || (this.j = !0, Gi(this.f, "RECEIVING"))
    };
    n.sa = function (a, b, c) {
        if (this.j && !this.g) {
            var d = b || {};
            switch (a) {
                case "addEventListener":
                    "string" === typeof d.event && (a = d.event, a in this.h || (c = C(this.Sa, this, a), this.h[a] = c, this.addEventListener(a, c)));
                    break;
                case "removeEventListener":
                    "string" === typeof d.event && Ii(this, d.event);
                    break;
                default:
                    this.i.isReady() && this.i.isExternalMethodAvailable(a, c || null) && (b = Ji(a, b || {}), c = this.i.handleExternalCall(a, b, c || null), (c = Ki(a, c)) && this.j && !this.g && Gi(this.f, a, c))
            }
        }
    };
    n.Sa = function (a, b) {
        this.j && !this.g && Gi(this.f, a, this.fa(a, b))
    };
    n.fa = function (a, b) {
        if (null != b) return {value: b}
    };

    function Ii(a, b) {
        b in a.h && (a.removeEventListener(b, a.h[b]), delete a.h[b])
    }

    n.o = function () {
        var a = this.f;
        a.g || Jd(a.f, "command", this.sa, this);
        this.f = null;
        for (var b in this.h) Ii(this, b);
        N.prototype.o.call(this)
    };

    function Li(a, b) {
        Hi.call(this, b);
        this.i = a;
        this.start()
    }

    t(Li, Hi);
    Li.prototype.addEventListener = function (a, b) {
        this.i.addEventListener(a, b)
    };
    Li.prototype.removeEventListener = function (a, b) {
        this.i.removeEventListener(a, b)
    };

    function Ji(a, b) {
        switch (a) {
            case "loadVideoById":
                return b = Ci(b), [b];
            case "cueVideoById":
                return b = Ci(b), [b];
            case "loadVideoByPlayerVars":
                return [b];
            case "cueVideoByPlayerVars":
                return [b];
            case "loadPlaylist":
                return b = Di(b), [b];
            case "cuePlaylist":
                return b = Di(b), [b];
            case "seekTo":
                return [b.seconds, b.allowSeekAhead];
            case "playVideoAt":
                return [b.index];
            case "setVolume":
                return [b.volume];
            case "setPlaybackQuality":
                return [b.suggestedQuality];
            case "setPlaybackRate":
                return [b.suggestedRate];
            case "setLoop":
                return [b.loopPlaylists];
            case "setShuffle":
                return [b.shufflePlaylist];
            case "getOptions":
                return [b.module];
            case "getOption":
                return [b.module, b.option];
            case "setOption":
                return [b.module, b.option, b.value];
            case "handleGlobalKeyDown":
                return [b.keyCode, b.shiftKey, b.ctrlKey, b.altKey, b.metaKey, b.key, b.code]
        }
        return []
    }

    function Ki(a, b) {
        switch (a) {
            case "isMuted":
                return {muted: b};
            case "getVolume":
                return {volume: b};
            case "getPlaybackRate":
                return {playbackRate: b};
            case "getAvailablePlaybackRates":
                return {availablePlaybackRates: b};
            case "getVideoLoadedFraction":
                return {videoLoadedFraction: b};
            case "getPlayerState":
                return {playerState: b};
            case "getCurrentTime":
                return {currentTime: b};
            case "getPlaybackQuality":
                return {playbackQuality: b};
            case "getAvailableQualityLevels":
                return {availableQualityLevels: b};
            case "getDuration":
                return {duration: b};
            case "getVideoUrl":
                return {videoUrl: b};
            case "getVideoEmbedCode":
                return {videoEmbedCode: b};
            case "getPlaylist":
                return {playlist: b};
            case "getPlaylistIndex":
                return {playlistIndex: b};
            case "getOptions":
                return {options: b};
            case "getOption":
                return {option: b}
        }
    }

    Li.prototype.fa = function (a, b) {
        switch (a) {
            case "onReady":
                return;
            case "onStateChange":
                return {playerState: b};
            case "onPlaybackQualityChange":
                return {playbackQuality: b};
            case "onPlaybackRateChange":
                return {playbackRate: b};
            case "onError":
                return {errorCode: b}
        }
        return Hi.prototype.fa.call(this, a, b)
    };
    Li.prototype.o = function () {
        Hi.prototype.o.call(this);
        delete this.i
    };

    function Mi(a, b, c) {
        N.call(this);
        var d = this;
        c = c || Q("POST_MESSAGE_ORIGIN", void 0) || window.document.location.protocol + "//" + window.document.location.hostname;
        this.h = b || null;
        this.w = "*";
        this.i = c;
        this.sessionId = null;
        this.channel = "widget";
        this.A = !!a;
        this.m = function (e) {
            a:if (!("*" != d.i && e.origin != d.i || d.h && e.source != d.h || "string" !== typeof e.data)) {
                try {
                    var f = JSON.parse(e.data)
                } catch (g) {
                    break a
                }
                if (!(null == f || d.A && (d.sessionId && d.sessionId != f.id || d.channel && d.channel != f.channel)) && f) switch (f.event) {
                    case "listening":
                        "null" !=
                        e.origin && (d.i = d.w = e.origin);
                        d.h = e.source;
                        d.sessionId = f.id;
                        d.f && (d.f(), d.f = null);
                        break;
                    case "command":
                        d.j && (!d.l || 0 <= La(d.l, f.func)) && d.j(f.func, f.args, e.origin)
                }
            }
        };
        this.l = this.f = this.j = null;
        window.addEventListener("message", this.m)
    }

    t(Mi, N);
    Mi.prototype.sendMessage = function (a, b) {
        var c = b || this.h;
        if (c) {
            this.sessionId && (a.id = this.sessionId);
            this.channel && (a.channel = this.channel);
            try {
                var d = JSON.stringify(a);
                c.postMessage(d, this.w)
            } catch (e) {
                ke(e)
            }
        }
    };
    Mi.prototype.o = function () {
        window.removeEventListener("message", this.m);
        N.prototype.o.call(this)
    };

    function Ni() {
        var a = this.g = new Mi(!!Q("WIDGET_ID_ENFORCE")), b = C(this.Pa, this);
        a.j = b;
        a.l = null;
        this.g.channel = "widget";
        if (a = Q("WIDGET_ID")) this.g.sessionId = a;
        this.i = [];
        this.l = !1;
        this.j = {}
    }

    n = Ni.prototype;
    n.Pa = function (a, b, c) {
        "addEventListener" == a && b ? (a = b[0], this.j[a] || "onReady" == a || (this.addEventListener(a, Oi(this, a)), this.j[a] = !0)) : this.na(a, b, c)
    };
    n.na = function () {
    };

    function Oi(a, b) {
        return C(function (c) {
            this.sendMessage(b, c)
        }, a)
    }

    n.addEventListener = function () {
    };
    n.ya = function () {
        this.l = !0;
        this.sendMessage("initialDelivery", this.ga());
        this.sendMessage("onReady");
        G(this.i, this.ta, this);
        this.i = []
    };
    n.ga = function () {
        return null
    };

    function Pi(a, b) {
        a.sendMessage("infoDelivery", b)
    }

    n.ta = function (a) {
        this.l ? this.g.sendMessage(a) : this.i.push(a)
    };
    n.sendMessage = function (a, b) {
        this.ta({event: a, info: void 0 == b ? null : b})
    };
    n.dispose = function () {
        this.g = null
    };

    function Qi(a) {
        Ni.call(this);
        this.f = a;
        this.h = [];
        this.addEventListener("onReady", C(this.Ma, this));
        this.addEventListener("onVideoProgress", C(this.Wa, this));
        this.addEventListener("onVolumeChange", C(this.Xa, this));
        this.addEventListener("onApiChange", C(this.Ra, this));
        this.addEventListener("onPlaybackQualityChange", C(this.Ta, this));
        this.addEventListener("onPlaybackRateChange", C(this.Ua, this));
        this.addEventListener("onStateChange", C(this.Va, this));
        this.addEventListener("onWebglSettingsChanged", C(this.Ya,
            this))
    }

    t(Qi, Ni);
    n = Qi.prototype;
    n.na = function (a, b, c) {
        if (this.f.isExternalMethodAvailable(a, c)) {
            b = b || [];
            if (0 < b.length && Ai(a)) {
                var d = b;
                if (Ca(d[0]) && !Aa(d[0])) d = d[0]; else {
                    var e = {};
                    switch (a) {
                        case "loadVideoById":
                        case "cueVideoById":
                            e = Ci.apply(window, d);
                            break;
                        case "loadVideoByUrl":
                        case "cueVideoByUrl":
                            e = Bi.apply(window, d);
                            break;
                        case "loadPlaylist":
                        case "cuePlaylist":
                            e = Di.apply(window, d)
                    }
                    d = e
                }
                b.length = 1;
                b[0] = d
            }
            this.f.handleExternalCall(a, b, c);
            Ai(a) && Pi(this, this.ga())
        }
    };
    n.Ma = function () {
        var a = C(this.ya, this);
        this.g.f = a
    };
    n.addEventListener = function (a, b) {
        this.h.push({eventType: a, listener: b});
        this.f.addEventListener(a, b)
    };
    n.ga = function () {
        if (!this.f) return null;
        var a = this.f.getApiInterface();
        Qa(a, "getVideoData");
        for (var b = {apiInterface: a}, c = 0, d = a.length; c < d; c++) {
            var e = a[c];
            if (0 == e.search("get") || 0 == e.search("is")) {
                var f = 0;
                0 == e.search("get") ? f = 3 : 0 == e.search("is") && (f = 2);
                f = e.charAt(f).toLowerCase() + e.substr(f + 1);
                try {
                    var g = this.f[e]();
                    b[f] = g
                } catch (h) {
                }
            }
        }
        b.videoData = this.f.getVideoData();
        b.currentTimeLastUpdated_ = D() / 1E3;
        return b
    };
    n.Va = function (a) {
        a = {
            playerState: a,
            currentTime: this.f.getCurrentTime(),
            duration: this.f.getDuration(),
            videoData: this.f.getVideoData(),
            videoStartBytes: 0,
            videoBytesTotal: this.f.getVideoBytesTotal(),
            videoLoadedFraction: this.f.getVideoLoadedFraction(),
            playbackQuality: this.f.getPlaybackQuality(),
            availableQualityLevels: this.f.getAvailableQualityLevels(),
            currentTimeLastUpdated_: D() / 1E3,
            playbackRate: this.f.getPlaybackRate(),
            mediaReferenceTime: this.f.getMediaReferenceTime()
        };
        this.f.getVideoUrl && (a.videoUrl =
            this.f.getVideoUrl());
        this.f.getVideoContentRect && (a.videoContentRect = this.f.getVideoContentRect());
        this.f.getProgressState && (a.progressState = this.f.getProgressState());
        this.f.getPlaylist && (a.playlist = this.f.getPlaylist());
        this.f.getPlaylistIndex && (a.playlistIndex = this.f.getPlaylistIndex());
        this.f.getStoryboardFormat && (a.storyboardFormat = this.f.getStoryboardFormat());
        Pi(this, a)
    };
    n.Ta = function (a) {
        Pi(this, {playbackQuality: a})
    };
    n.Ua = function (a) {
        Pi(this, {playbackRate: a})
    };
    n.Ra = function () {
        for (var a = this.f.getOptions(), b = {namespaces: a}, c = 0, d = a.length; c < d; c++) {
            var e = a[c], f = this.f.getOptions(e);
            b[e] = {options: f};
            for (var g = 0, h = f.length; g < h; g++) {
                var k = f[g], l = this.f.getOption(e, k);
                b[e][k] = l
            }
        }
        this.sendMessage("apiInfoDelivery", b)
    };
    n.Xa = function () {
        Pi(this, {muted: this.f.isMuted(), volume: this.f.getVolume()})
    };
    n.Wa = function (a) {
        a = {
            currentTime: a,
            videoBytesLoaded: this.f.getVideoBytesLoaded(),
            videoLoadedFraction: this.f.getVideoLoadedFraction(),
            currentTimeLastUpdated_: D() / 1E3,
            playbackRate: this.f.getPlaybackRate(),
            mediaReferenceTime: this.f.getMediaReferenceTime()
        };
        this.f.getProgressState && (a.progressState = this.f.getProgressState());
        Pi(this, a)
    };
    n.Ya = function () {
        var a = {sphericalProperties: this.f.getSphericalProperties()};
        Pi(this, a)
    };
    n.dispose = function () {
        Ni.prototype.dispose.call(this);
        for (var a = 0; a < this.h.length; a++) {
            var b = this.h[a];
            this.f.removeEventListener(b.eventType, b.listener)
        }
        this.h = []
    };

    function Ri(a, b, c) {
        N.call(this);
        this.f = a;
        this.i = c;
        this.j = V(window, "message", C(this.l, this));
        this.h = new Fi(this, a, b);
        Ad(this, Ja(Bd, this.h))
    }

    t(Ri, N);
    Ri.prototype.l = function (a) {
        var b;
        if (b = !this.g) if (b = a.origin == this.i) a:{
            b = this.f;
            do {
                b:{
                    var c = a.source;
                    do {
                        if (c == b) {
                            c = !0;
                            break b
                        }
                        if (c == c.parent) break;
                        c = c.parent
                    } while (null != c);
                    c = !1
                }
                if (c) {
                    b = !0;
                    break a
                }
                b = b.opener
            } while (null != b);
            b = !1
        }
        if (b && (b = a.data, "string" === typeof b)) {
            try {
                b = JSON.parse(b)
            } catch (d) {
                return
            }
            b.command && (c = this.h, c.g || c.j("command", b.command, b.data, a.origin))
        }
    };
    Ri.prototype.o = function () {
        $f(this.j);
        this.f = null;
        N.prototype.o.call(this)
    };

    function Si() {
        var a = lb(Ti), b;
        return kd(new M(function (c, d) {
            a.onSuccess = function (e) {
                ye(e) ? c(e) : d(new Ui("Request failed, status=" + (e && "status" in e ? e.status : -1), "net.badstatus", e))
            };
            a.onError = function (e) {
                d(new Ui("Unknown request error", "net.unknown", e))
            };
            a.O = function (e) {
                d(new Ui("Request timed out", "net.timeout", e))
            };
            b = Ie("//googleads.g.doubleclick.net/pagead/id", a)
        }), function (c) {
            c instanceof ld && b.abort();
            return id(c)
        })
    }

    function Ui(a, b) {
        F.call(this, a + ", errorCode=" + b);
        this.errorCode = b;
        this.name = "PromiseAjaxError"
    }

    t(Ui, F);

    function Vi() {
        this.g = 0;
        this.f = null
    }

    Vi.prototype.then = function (a, b, c) {
        return 1 === this.g && a ? (a = a.call(c, this.f), Qc(a) ? a : Wi(a)) : 2 === this.g && b ? (a = b.call(c, this.f), Qc(a) ? a : Xi(a)) : this
    };
    Vi.prototype.getValue = function () {
        return this.f
    };
    Vi.prototype.$goog_Thenable = !0;

    function Xi(a) {
        var b = new Vi;
        a = void 0 === a ? null : a;
        b.g = 2;
        b.f = void 0 === a ? null : a;
        return b
    }

    function Wi(a) {
        var b = new Vi;
        a = void 0 === a ? null : a;
        b.g = 1;
        b.f = void 0 === a ? null : a;
        return b
    }

    function Yi(a) {
        F.call(this, a.message || a.description || a.name);
        this.isMissing = a instanceof Zi;
        this.isTimeout = a instanceof Ui && "net.timeout" == a.errorCode;
        this.isCanceled = a instanceof ld
    }

    t(Yi, F);
    Yi.prototype.name = "BiscottiError";

    function Zi() {
        F.call(this, "Biscotti ID is missing from server")
    }

    t(Zi, F);
    Zi.prototype.name = "BiscottiMissingError";
    var Ti = {format: "RAW", method: "GET", timeout: 5E3, withCredentials: !0}, $i = null;

    function ee() {
        if ("1" === fb(ce(), "args", "privembed")) return id(Error("Biscotti ID is not available in private embed mode"));
        $i || ($i = kd(Si().then(aj), function (a) {
            return bj(2, a)
        }));
        return $i
    }

    function aj(a) {
        a = a.responseText;
        if (0 != a.lastIndexOf(")]}'", 0)) throw new Zi;
        a = JSON.parse(a.substr(4));
        if (1 < (a.type || 1)) throw new Zi;
        a = a.id;
        fe(a);
        $i = Wi(a);
        cj(18E5, 2);
        return a
    }

    function bj(a, b) {
        var c = new Yi(b);
        fe("");
        $i = Xi(c);
        0 < a && cj(12E4, a - 1);
        throw c;
    }

    function cj(a, b) {
        S(function () {
            kd(Si().then(aj, function (c) {
                return bj(b, c)
            }), xa)
        }, a)
    }

    function dj() {
        try {
            var a = z("yt.ads.biscotti.getId_");
            return a ? a() : ee()
        } catch (b) {
            return id(b)
        }
    }

    function ej(a) {
        if ("1" !== fb(ce(), "args", "privembed")) {
            a && de();
            try {
                dj().then(function () {
                }, function () {
                }), S(ej, 18E5)
            } catch (b) {
                je(b)
            }
        }
    }

    var Z = z("ytglobal.prefsUserPrefsPrefs_") || {};
    y("ytglobal.prefsUserPrefsPrefs_", Z, void 0);

    function fj() {
        this.f = Q("ALT_PREF_COOKIE_NAME", "PREF");
        var a = Vb.get("" + this.f, void 0);
        if (a) {
            a = decodeURIComponent(a).split("&");
            for (var b = 0; b < a.length; b++) {
                var c = a[b].split("="), d = c[0];
                (c = c[1]) && (Z[d] = c.toString())
            }
        }
    }

    n = fj.prototype;
    n.get = function (a, b) {
        gj(a);
        hj(a);
        var c = void 0 !== Z[a] ? Z[a].toString() : null;
        return null != c ? c : b ? b : ""
    };
    n.set = function (a, b) {
        gj(a);
        hj(a);
        if (null == b) throw Error("ExpectedNotNull");
        Z[a] = b.toString()
    };
    n.remove = function (a) {
        gj(a);
        hj(a);
        delete Z[a]
    };
    n.save = function () {
        Hg(this.f, this.dump(), 63072E3)
    };
    n.clear = function () {
        for (var a in Z) delete Z[a]
    };
    n.dump = function () {
        var a = [], b;
        for (b in Z) a.push(b + "=" + encodeURIComponent(String(Z[b])));
        return a.join("&")
    };

    function hj(a) {
        if (/^f([1-9][0-9]*)$/.test(a)) throw Error("ExpectedRegexMatch: " + a);
    }

    function gj(a) {
        if (!/^\w+$/.test(a)) throw Error("ExpectedRegexMismatch: " + a);
    }

    function ij(a) {
        a = void 0 !== Z[a] ? Z[a].toString() : null;
        return null != a && /^[A-Fa-f0-9]+$/.test(a) ? parseInt(a, 16) : null
    }

    ya(fj);
    var jj = null, kj = null, lj = null, mj = {};

    function nj(a) {
        var b = a.id;
        a = a.ve_type;
        var c = Tg++;
        a = new Rg({veType: a, veCounter: c, elementIndex: void 0, dataElement: void 0, youtubeData: void 0});
        mj[b] = a;
        b = Zg();
        c = Xg();
        b && c && th(b, c, [a])
    }

    function oj(a) {
        var b = a.csn;
        a = a.root_ve_type;
        if (b && a && (bh(b, a), a = Xg())) for (var c in mj) {
            var d = mj[c];
            d && th(b, a, [d])
        }
    }

    function pj(a) {
        mj[a.id] = new Rg({trackingParams: a.tracking_params})
    }

    function qj(a) {
        var b = Zg();
        a = mj[a.id];
        if (b && a) {
            a = {csn: b, ve: Sg(a), gestureType: "INTERACTION_LOGGING_GESTURE_TYPE_GENERIC_CLICK"};
            var c = {da: ah(b), P: b};
            "UNDEFINED_CSN" == b ? uh("visualElementGestured", a, c) : Dg("visualElementGestured", a, Ng, c)
        }
    }

    function rj(a) {
        a = a.ids;
        var b = Zg();
        if (b) for (var c = 0; c < a.length; c++) {
            var d = mj[a[c]];
            if (d) {
                var e = b;
                d = {csn: e, ve: Sg(d), eventType: 1};
                var f = {da: ah(e), P: e};
                "UNDEFINED_CSN" == e ? uh("visualElementShown", d, f) : Dg("visualElementShown", d, Ng, f)
            }
        }
    }

    y("yt.setConfig", P, void 0);
    y("yt.config.set", P, void 0);
    y("yt.setMsg", bf, void 0);
    y("yt.msgs.set", bf, void 0);
    y("yt.logging.errors.log", $e, void 0);
    y("writeEmbed", function () {
        var a = Q("PLAYER_CONFIG", void 0);
        ej(!0);
        "gvn" == a.args.ps && (document.body.style.backgroundColor = "transparent");
        var b = document.referrer, c = Q("POST_MESSAGE_ORIGIN");
        window != window.top && b && b != document.URL && (a.args.loaderUrl = b);
        Q("LIGHTWEIGHT_AUTOPLAY") && (a.args.autoplay = "1");
        b = "player";
        var d = void 0 === d ? !0 : d;
        b = "string" === typeof b ? ac(b) : b;
        var e = Sh + "_" + Da(b), f = Rh[e];
        f && d ? a && a.args && a.args.fflags && a.args.fflags.includes("web_player_remove_playerproxy=true") ? f.api.loadVideoByPlayerVars(a.args ||
            null) : f.loadNewVideoConfig(a) : (f = new Ch(b, e, a, void 0), Rh[e] = f, of("player-added", f.api), Ad(f, Ja(Th, f)));
        a = f.api;
        jj = a;
        a.addEventListener("onScreenChanged", oj);
        a.addEventListener("onLogClientVeCreated", nj);
        a.addEventListener("onLogServerVeCreated", pj);
        a.addEventListener("onLogVeClicked", qj);
        a.addEventListener("onLogVesShown", rj);
        d = Q("POST_MESSAGE_ID", "player");
        Q("ENABLE_JS_API") ? lj = new Qi(a) : Q("ENABLE_POST_API") && "string" === typeof d && "string" === typeof c && (kj = new Ri(window.parent, d, c), lj = new Li(a, kj.h));
        c = Q("BG_P", void 0);
        Qf(c) && (Q("BG_I") || Q("BG_IU")) && (Mf = !0, Lf.initialize(Q("BG_I", null), Q("BG_IU", null), c, Pf, void 0));
        Gf()
    }, void 0);
    y("yt.www.watch.ads.restrictioncookie.spr", function (a) {
        Qe(a + "mac_204?action_fcts=1");
        return !0
    }, void 0);
    var sj = ie(function () {
        var a = ai(void 0), b;
        (b = !R("use_first_tick")) || (b = !("ol" in ai(void 0)));
        if (b && (b = "ol", X.mark && (0 == b.lastIndexOf("mark_", 0) || (b = "mark_" + b), X.mark(b)), b = U(), a.ol = b, a = xi(void 0), a.gelTicks && (a.gelTicks.tick_ol = !0), U(), wi(void 0) ? (li("").tick.ol = U(), a = di(void 0), pi().tick("ol", a, void 0), ei(), a = !0) : a = !1, !a)) {
            if (!z("yt.timing.pingSent_") && (b = Q("TIMING_ACTION", void 0), a = ai(void 0), z("ytglobal.timingready_") && b && a._start && $h())) {
                ei();
                b = !0;
                var c = Q("TIMING_WAIT", []);
                if (c.length) for (var d =
                    0, e = c.length; d < e; ++d) if (!(c[d] in a)) {
                    b = !1;
                    break
                }
                if (b && !wi(void 0)) {
                    d = ai(void 0);
                    c = ci(void 0);
                    e = d._start;
                    var f = Q("CSI_SERVICE_NAME", "youtube");
                    b = {v: 2, s: f, action: Q("TIMING_ACTION", void 0)};
                    a = c.srt;
                    void 0 !== d.srt && delete c.srt;
                    if (c.h5jse) {
                        var g = window.location.protocol + z("ytplayer.config.assets.js");
                        (g = X.getEntriesByName ? X.getEntriesByName(g)[0] : null) ? c.h5jse = Math.round(c.h5jse - g.responseEnd) : delete c.h5jse
                    }
                    d.aft = $h();
                    var h = ai(void 0);
                    g = h.pbr;
                    var k = h.vc;
                    h = h.pbs;
                    g && k && h && g < k && k < h && ci(void 0).yt_pvis &&
                    "youtube" == f && (yi("yt_lt", "hot_bg", void 0), f = d.vc, g = d.pbs, delete d.aft, c.aft = Math.round(g - f));
                    for (var l in c) "_" != l.charAt(0) && (b[l] = c[l]);
                    d.ps = U();
                    l = {};
                    f = [];
                    for (var m in d) "_" != m.charAt(0) && (g = Math.round(d[m] - e), l[m] = g, f.push(m + "." + g));
                    b.rt = f.join(",");
                    (m = z("ytdebug.logTiming")) && m(b, l);
                    m = !!c.ap;
                    R("debug_csi_data") && (c = z("yt.timing.csiData"), c || (c = [], Ka("yt.timing.csiData", c)), c.push({
                        page: location.href,
                        time: new Date,
                        args: b
                    }));
                    c = "";
                    for (var p in b) b.hasOwnProperty(p) && (c += "&" + p + "=" + b[p]);
                    p = "/csi_204?" +
                        c.substring(1);
                    if (window.navigator && window.navigator.sendBeacon && m) {
                        var v = void 0 === v ? "" : v;
                        Te(p, v) || Qe(p, void 0, void 0, void 0, v)
                    } else Qe(p);
                    y("yt.timing.pingSent_", !0, void 0);
                    jh(Yh, new Wh(l.aft + (a || 0), void 0))
                }
            }
            li("").tick.ol = U()
        }
        v = fj.getInstance();
        m = !!((ij("f" + (Math.floor(119 / 31) + 1)) || 0) & 67108864);
        p = 1 < window.devicePixelRatio;
        document.body && Fc(document.body, "exp-invert-logo") && (p && !Fc(document.body, "inverted-hdpi") ? (l = document.body, l.classList ? l.classList.add("inverted-hdpi") : Fc(l, "inverted-hdpi") ||
            (a = Cc(l), Ec(l, a + (0 < a.length ? " inverted-hdpi" : "inverted-hdpi")))) : !p && Fc(document.body, "inverted-hdpi") && Gc());
        m != p && (m = "f" + (Math.floor(119 / 31) + 1), l = ij(m) || 0, l = p ? l | 67108864 : l & -67108865, 0 == l ? delete Z[m] : (p = l.toString(16), Z[m] = p.toString()), v.save())
    }), tj = ie(function () {
        var a = jj;
        a && a.sendAbandonmentPing && a.sendAbandonmentPing();
        Q("PL_ATT") && Lf.dispose();
        a = 0;
        for (var b = Ef.length; a < b; a++) {
            var c = Ef[a];
            if (!isNaN(c)) {
                var d = z("yt.scheduler.instance.cancelJob");
                d ? d(c) : T(c)
            }
        }
        Ef.length = 0;
        yf("//static.doubleclick.net/instream/ad_status.js");
        Ff = !1;
        P("DCLKSTAT", 0);
        Cd(lj, kj);
        if (a = jj) a.removeEventListener("onScreenChanged", oj), a.removeEventListener("onLogClientVeCreated", nj), a.removeEventListener("onLogServerVeCreated", pj), a.removeEventListener("onLogVeClicked", qj), a.removeEventListener("onLogVesShown",
            rj), a.destroy();
        mj = {}
    });
    window.addEventListener ? (window.addEventListener("load", sj), window.addEventListener("unload", tj)) : window.attachEvent && (window.attachEvent("onload", sj), window.attachEvent("onunload", tj));
    Ka("yt.abuse.player.botguardInitialized", z("yt.abuse.player.botguardInitialized") || Rf);
    Ka("yt.abuse.player.invokeBotguard", z("yt.abuse.player.invokeBotguard") || Sf);
    Ka("yt.abuse.dclkstatus.checkDclkStatus", z("yt.abuse.dclkstatus.checkDclkStatus") || Hf);
    Ka("yt.player.exports.navigate", z("yt.player.exports.navigate") || ch);
    Ka("yt.util.activity.init", z("yt.util.activity.init") || dg);
    Ka("yt.util.activity.getTimeSinceActive", z("yt.util.activity.getTimeSinceActive") || gg);
    Ka("yt.util.activity.setTimestamp", z("yt.util.activity.setTimestamp") || eg);
}).call(this);
