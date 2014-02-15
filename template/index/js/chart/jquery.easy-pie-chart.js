(function(a) {
    a.easyPieChart = function(d, l) {
        var f, g, i, j, c, k, e, b, h = this;
        this.el = d;
        this.$el = a(d);
        this.$el.data("easyPieChart", this);
        this.init = function() {
            var n, m;
            h.options = a.extend({}, a.easyPieChart.defaultOptions, l);
            n = parseInt(h.$el.data("percent"), 10);
            h.percentage = 0;
            h.canvas = a("<canvas width='" + h.options.size + "' height='" + h.options.size + "'></canvas>").get(0);
            h.$el.append(h.canvas);
            if (typeof G_vmlCanvasManager !== "undefined" && G_vmlCanvasManager !== null) {
                G_vmlCanvasManager.initElement(h.canvas)
            }
            h.ctx = h.canvas.getContext("2d");
            if (window.devicePixelRatio > 1) {
                m = window.devicePixelRatio;
                a(h.canvas).css({
                    width: h.options.size,
                    height: h.options.size
                });
                h.canvas.width *= m;
                h.canvas.height *= m;
                h.ctx.scale(m, m)
            }
            h.ctx.translate(h.options.size / 2, h.options.size / 2);
            h.ctx.rotate(h.options.rotate * Math.PI / 180);
            h.$el.addClass("easyPieChart");
            h.$el.css({
                width: h.options.size,
                height: h.options.size,
                lineHeight: "" + h.options.size + "px"
            });
            h.update(n);
            return h
        };
        this.update = function(m) {
            m = parseFloat(m) || 0;
            if (h.options.animate === false) {
                i(m)
            } else {
                if (h.options.delay) {
                    g(h.percentage, 0);
                    setTimeout(function() {
                        return g(h.percentage, m)
                    }, h.options.delay)
                } else {
                    g(h.percentage, m)
                }
            }
            return h
        };
        e = function() {
            var n, o, m;
            h.ctx.fillStyle = h.options.scaleColor;
            h.ctx.lineWidth = 1;
            m = [];
            for (n = o = 0; o <= 24; n = ++o) {
                m.push(f(n))
            }
            return m
        };
        f = function(m) {
            var n;
            n = m % 6 === 0 ? 0 : h.options.size * 0.017;
            h.ctx.save();
            h.ctx.rotate(m * Math.PI / 12);
            h.ctx.fillRect(h.options.size / 2 - n, 0, -h.options.size * 0.05 + n, 1);
            h.ctx.restore()
        };
        b = function() {
            var m;
            m = h.options.size / 2 - h.options.lineWidth / 2;
            if (h.options.scaleColor !== false) {
                m -= h.options.size * 0.08
            }
            h.ctx.beginPath();
            h.ctx.arc(0, 0, m, 0, Math.PI * 2, true);
            h.ctx.closePath();
            h.ctx.strokeStyle = h.options.trackColor;
            h.ctx.lineWidth = h.options.lineWidth;
            h.ctx.stroke()
        };
        k = function() {
            if (h.options.scaleColor !== false) {
                e()
            }
            if (h.options.trackColor !== false) {
                b()
            }
        };
        i = function(m) {
            var n;
            k();
            h.ctx.strokeStyle = a.isFunction(h.options.barColor) ? h.options.barColor(m) : h.options.barColor;
            h.ctx.lineCap = h.options.lineCap;
            h.ctx.lineWidth = h.options.lineWidth;
            n = h.options.size / 2 - h.options.lineWidth / 2;
            if (h.options.scaleColor !== false) {
                n -= h.options.size * 0.08
            }
            h.ctx.save();
            h.ctx.rotate(-Math.PI / 2);
            h.ctx.beginPath();
            h.ctx.arc(0, 0, n, 0, Math.PI * 2 * m / 100, false);
            h.ctx.stroke();
            h.ctx.restore()
        };
        c = (function() {
            return window.requestAnimationFrame || window.webkitRequestAnimationFrame || window.mozRequestAnimationFrame || function(m) {
                return window.setTimeout(m, 1000 / 60)
            }
        })();
        g = function(p, o) {
            var n, m;
            h.options.onStart.call(h);
            h.percentage = o;
            Date.now || (Date.now = function() {
                return +(new Date)
            });
            m = Date.now();
            n = function() {
                var q, r;
                r = Math.min(Date.now() - m, h.options.animate);
                h.ctx.clearRect(-h.options.size / 2, -h.options.size / 2, h.options.size, h.options.size);
                k.call(h);
                q = [j(r, p, o - p, h.options.animate)];
                h.options.onStep.call(h, q);
                i.call(h, q);
                if (r >= h.options.animate) {
                    return h.options.onStop.call(h, q, o)
                } else {
                    return c(n)
                }
            };
            c(n)
        };
        j = function(o, n, r, p) {
            var m, q;
            m = function(s) {
                return Math.pow(s, 2)
            };
            q = function(s) {
                if (s < 1) {
                    return m(s)
                } else {
                    return 2 - m((s / 2) * -2 + 2)
                }
            };
            o /= p / 2;
            return r / 2 * q(o) + n
        };
        return this.init()
    };
    a.easyPieChart.defaultOptions = {
        barColor: "#ef1e25",
        trackColor: "#f2f2f2",
        scaleColor: "#dfe0e0",
        lineCap: "round",
        rotate: 0,
        size: 110,
        lineWidth: 3,
        animate: false,
        delay: false,
        onStart: a.noop,
        onStop: a.noop,
        onStep: a.noop
    };
    a.fn.easyPieChart = function(b) {
        return a.each(this, function(d, e) {
            var c, f;
            c = a(e);
            if (!c.data("easyPieChart")) {
                f = a.extend({}, b, c.data());
                return c.data("easyPieChart", new a.easyPieChart(e, f))
            }
        })
    };
    return void 0
})(jQuery);
