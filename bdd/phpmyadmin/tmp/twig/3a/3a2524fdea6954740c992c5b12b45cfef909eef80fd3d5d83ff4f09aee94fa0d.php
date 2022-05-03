<?php

use Twig\Environment;
use Twig\Error\LoaderError;
use Twig\Error\RuntimeError;
use Twig\Extension\SandboxExtension;
use Twig\Markup;
use Twig\Sandbox\SecurityError;
use Twig\Sandbox\SecurityNotAllowedTagError;
use Twig\Sandbox\SecurityNotAllowedFilterError;
use Twig\Sandbox\SecurityNotAllowedFunctionError;
use Twig\Source;
use Twig\Template;

/* gis_data_editor_form.twig */
class __TwigTemplate_1d44dfb71b4d58402546b8c434e57afadbc8e5744cf2defc6eb64b85250182f6 extends \Twig\Template
{
    private $source;
    private $macros = [];

    public function __construct(Environment $env)
    {
        parent::__construct($env);

        $this->source = $this->getSourceContext();

        $this->parent = false;

        $this->blocks = [
        ];
    }

    protected function doDisplay(array $context, array $blocks = [])
    {
        $macros = $this->macros;
        // line 1
        echo "<form id=\"gis_data_editor_form\" action=\"";
        echo PhpMyAdmin\Url::getFromRoute("/gis-data-editor");
        echo "\" method=\"post\">
    <input type=\"hidden\" id=\"themeImagePath\" value=\"";
        // line 2
        echo twig_escape_filter($this->env, ($context["theme_image_path"] ?? null), "html", null, true);
        echo "\">
    <div id=\"gis_data_editor\">
        <h3>";
        // line 4
        echo twig_escape_filter($this->env, sprintf(_gettext("Value for the column \"%s\""), ($context["field"] ?? null)), "html", null, true);
        echo "</h3>

        <input type=\"hidden\" name=\"field\" value=\"";
        // line 6
        echo twig_escape_filter($this->env, ($context["field"] ?? null), "html", null, true);
        echo "\">
        ";
        // line 8
        echo "        ";
        if ( !(null === ($context["input_name"] ?? null))) {
            // line 9
            echo "            <input type=\"hidden\" name=\"input_name\" value=\"";
            echo twig_escape_filter($this->env, ($context["input_name"] ?? null), "html", null, true);
            echo "\">
        ";
        }
        // line 11
        echo "        ";
        echo PhpMyAdmin\Url::getHiddenInputs();
        echo "

        ";
        // line 14
        echo "        <div id=\"placeholder\"";
        echo (((($context["srid"] ?? null) != 0)) ? (" class=\"hide\"") : (""));
        echo ">
            ";
        // line 15
        echo ($context["visualization"] ?? null);
        echo "
        </div>

        <div id=\"openlayersmap\" style=\"width: ";
        // line 18
        echo twig_escape_filter($this->env, ($context["width"] ?? null), "html", null, true);
        echo "px; height: ";
        echo twig_escape_filter($this->env, ($context["height"] ?? null), "html", null, true);
        echo "px;\"";
        echo (((($context["srid"] ?? null) == 0)) ? (" class=\"hide\"") : (""));
        echo "></div>

        <div class=\"choice floatright\">
            <input type=\"checkbox\" id=\"choice\" value=\"useBaseLayer\"";
        // line 21
        echo (((($context["srid"] ?? null) != 0)) ? (" checked=\"checked\"") : (""));
        echo ">
            <label for=\"choice\">";
        // line 22
        echo _gettext("Use OpenStreetMaps as Base Layer");
        echo "</label>
        </div>

        <script language=\"javascript\" type=\"text/javascript\">";
        // line 25
        echo ($context["open_layers"] ?? null);
        echo "</script>
        ";
        // line 27
        echo "
        ";
        // line 29
        echo "        <div id=\"gis_data_header\">
            <select name=\"gis_data[gis_type]\" class=\"gis_type\">
                ";
        // line 31
        $context['_parent'] = $context;
        $context['_seq'] = twig_ensure_traversable(($context["gis_types"] ?? null));
        foreach ($context['_seq'] as $context["_key"] => $context["gis_type"]) {
            // line 32
            echo "                    <option value=\"";
            echo twig_escape_filter($this->env, $context["gis_type"], "html", null, true);
            echo "\"";
            echo (((($context["geom_type"] ?? null) == $context["gis_type"])) ? (" selected=\"selected\"") : (""));
            echo ">
                        ";
            // line 33
            echo twig_escape_filter($this->env, $context["gis_type"], "html", null, true);
            echo "
                    </option>
                ";
        }
        $_parent = $context['_parent'];
        unset($context['_seq'], $context['_iterated'], $context['_key'], $context['gis_type'], $context['_parent'], $context['loop']);
        $context = array_intersect_key($context, $_parent) + $_parent;
        // line 36
        echo "            </select>

            <label for=\"srid\">";
        // line 38
        echo _pgettext(        "Spatial Reference System Identifier", "SRID:");
        echo "</label>
            <input name=\"gis_data[srid]\" type=\"text\" value=\"";
        // line 39
        echo twig_escape_filter($this->env, ($context["srid"] ?? null), "html", null, true);
        echo "\">
        </div>
        ";
        // line 42
        echo "
        ";
        // line 44
        echo "        <div id=\"gis_data\">
            ";
        // line 45
        if ((($context["geom_type"] ?? null) == "GEOMETRYCOLLECTION")) {
            // line 46
            echo "                <input type=\"hidden\" name=\"gis_data[GEOMETRYCOLLECTION][geom_count]\" value=\"";
            echo twig_escape_filter($this->env, ($context["geom_count"] ?? null), "html", null, true);
            echo "\">
            ";
        }
        // line 48
        echo "
            ";
        // line 49
        $context['_parent'] = $context;
        $context['_seq'] = twig_ensure_traversable(range(0, (($context["geom_count"] ?? null) - 1)));
        foreach ($context['_seq'] as $context["_key"] => $context["a"]) {
            // line 50
            echo "              ";
            if ( !(null === (($__internal_f607aeef2c31a95a7bf963452dff024ffaeb6aafbe4603f9ca3bec57be8633f4 = ($context["gis_data"] ?? null)) && is_array($__internal_f607aeef2c31a95a7bf963452dff024ffaeb6aafbe4603f9ca3bec57be8633f4) || $__internal_f607aeef2c31a95a7bf963452dff024ffaeb6aafbe4603f9ca3bec57be8633f4 instanceof ArrayAccess ? ($__internal_f607aeef2c31a95a7bf963452dff024ffaeb6aafbe4603f9ca3bec57be8633f4[$context["a"]] ?? null) : null))) {
                // line 51
                echo "                ";
                if ((($context["geom_type"] ?? null) == "GEOMETRYCOLLECTION")) {
                    // line 52
                    echo "                    <br><br>
                    ";
                    // line 53
                    echo twig_escape_filter($this->env, sprintf(_gettext("Geometry %d:"), ($context["a"] + 1)), "html", null, true);
                    echo "
                    <br>
                    ";
                    // line 55
                    if ( !(null === (($__internal_62824350bc4502ee19dbc2e99fc6bdd3bd90e7d8dd6e72f42c35efd048542144 = (($__internal_1cfccaec8dd2e8578ccb026fbe7f2e7e29ac2ed5deb976639c5fc99a6ea8583b = ($context["gis_data"] ?? null)) && is_array($__internal_1cfccaec8dd2e8578ccb026fbe7f2e7e29ac2ed5deb976639c5fc99a6ea8583b) || $__internal_1cfccaec8dd2e8578ccb026fbe7f2e7e29ac2ed5deb976639c5fc99a6ea8583b instanceof ArrayAccess ? ($__internal_1cfccaec8dd2e8578ccb026fbe7f2e7e29ac2ed5deb976639c5fc99a6ea8583b[$context["a"]] ?? null) : null)) && is_array($__internal_62824350bc4502ee19dbc2e99fc6bdd3bd90e7d8dd6e72f42c35efd048542144) || $__internal_62824350bc4502ee19dbc2e99fc6bdd3bd90e7d8dd6e72f42c35efd048542144 instanceof ArrayAccess ? ($__internal_62824350bc4502ee19dbc2e99fc6bdd3bd90e7d8dd6e72f42c35efd048542144["gis_type"] ?? null) : null))) {
                        // line 56
                        echo "                        ";
                        $context["type"] = (($__internal_68aa442c1d43d3410ea8f958ba9090f3eaa9a76f8de8fc9be4d6c7389ba28002 = (($__internal_d7fc55f1a54b629533d60b43063289db62e68921ee7a5f8de562bd9d4a2b7ad4 = ($context["gis_data"] ?? null)) && is_array($__internal_d7fc55f1a54b629533d60b43063289db62e68921ee7a5f8de562bd9d4a2b7ad4) || $__internal_d7fc55f1a54b629533d60b43063289db62e68921ee7a5f8de562bd9d4a2b7ad4 instanceof ArrayAccess ? ($__internal_d7fc55f1a54b629533d60b43063289db62e68921ee7a5f8de562bd9d4a2b7ad4[$context["a"]] ?? null) : null)) && is_array($__internal_68aa442c1d43d3410ea8f958ba9090f3eaa9a76f8de8fc9be4d6c7389ba28002) || $__internal_68aa442c1d43d3410ea8f958ba9090f3eaa9a76f8de8fc9be4d6c7389ba28002 instanceof ArrayAccess ? ($__internal_68aa442c1d43d3410ea8f958ba9090f3eaa9a76f8de8fc9be4d6c7389ba28002["gis_type"] ?? null) : null);
                        // line 57
                        echo "                    ";
                    } else {
                        // line 58
                        echo "                        ";
                        $context["type"] = (($__internal_01476f8db28655ee4ee02ea2d17dd5a92599be76304f08cd8bc0e05aced30666 = ($context["gis_types"] ?? null)) && is_array($__internal_01476f8db28655ee4ee02ea2d17dd5a92599be76304f08cd8bc0e05aced30666) || $__internal_01476f8db28655ee4ee02ea2d17dd5a92599be76304f08cd8bc0e05aced30666 instanceof ArrayAccess ? ($__internal_01476f8db28655ee4ee02ea2d17dd5a92599be76304f08cd8bc0e05aced30666[0] ?? null) : null);
                        // line 59
                        echo "                    ";
                    }
                    // line 60
                    echo "                    <select name=\"gis_data[";
                    echo twig_escape_filter($this->env, $context["a"], "html", null, true);
                    echo "][gis_type]\" class=\"gis_type\">
                        ";
                    // line 61
                    $context['_parent'] = $context;
                    $context['_seq'] = twig_ensure_traversable(twig_slice($this->env, ($context["gis_types"] ?? null), 0, 6));
                    foreach ($context['_seq'] as $context["_key"] => $context["gis_type"]) {
                        // line 62
                        echo "                            <option value=\"";
                        echo twig_escape_filter($this->env, $context["gis_type"], "html", null, true);
                        echo "\"";
                        echo (((($context["type"] ?? null) == $context["gis_type"])) ? (" selected=\"selected\"") : (""));
                        echo ">
                                ";
                        // line 63
                        echo twig_escape_filter($this->env, $context["gis_type"], "html", null, true);
                        echo "
                            </option>
                        ";
                    }
                    $_parent = $context['_parent'];
                    unset($context['_seq'], $context['_iterated'], $context['_key'], $context['gis_type'], $context['_parent'], $context['loop']);
                    $context = array_intersect_key($context, $_parent) + $_parent;
                    // line 66
                    echo "                    </select>
                ";
                } else {
                    // line 68
                    echo "                    ";
                    $context["type"] = ($context["geom_type"] ?? null);
                    // line 69
                    echo "                ";
                }
                // line 70
                echo "
                ";
                // line 71
                if ((($context["type"] ?? null) == "POINT")) {
                    // line 72
                    echo "                    <br>
                    ";
                    // line 73
                    echo _gettext("Point:");
                    // line 74
                    echo "                    <label for=\"x\">";
                    echo _gettext("X");
                    echo "</label>
                    <input name=\"gis_data[";
                    // line 75
                    echo twig_escape_filter($this->env, $context["a"], "html", null, true);
                    echo "][POINT][x]\" type=\"text\" value=\"";
                    echo twig_escape_filter($this->env, (($__internal_01c35b74bd85735098add188b3f8372ba465b232ab8298cb582c60f493d3c22e = (($__internal_63ad1f9a2bf4db4af64b010785e9665558fdcac0e8db8b5b413ed986c62dbb52 = (($__internal_f10a4cc339617934220127f034125576ed229e948660ebac906a15846d52f136 = ($context["gis_data"] ?? null)) && is_array($__internal_f10a4cc339617934220127f034125576ed229e948660ebac906a15846d52f136) || $__internal_f10a4cc339617934220127f034125576ed229e948660ebac906a15846d52f136 instanceof ArrayAccess ? ($__internal_f10a4cc339617934220127f034125576ed229e948660ebac906a15846d52f136[$context["a"]] ?? null) : null)) && is_array($__internal_63ad1f9a2bf4db4af64b010785e9665558fdcac0e8db8b5b413ed986c62dbb52) || $__internal_63ad1f9a2bf4db4af64b010785e9665558fdcac0e8db8b5b413ed986c62dbb52 instanceof ArrayAccess ? ($__internal_63ad1f9a2bf4db4af64b010785e9665558fdcac0e8db8b5b413ed986c62dbb52["POINT"] ?? null) : null)) && is_array($__internal_01c35b74bd85735098add188b3f8372ba465b232ab8298cb582c60f493d3c22e) || $__internal_01c35b74bd85735098add188b3f8372ba465b232ab8298cb582c60f493d3c22e instanceof ArrayAccess ? ($__internal_01c35b74bd85735098add188b3f8372ba465b232ab8298cb582c60f493d3c22e["x"] ?? null) : null), "html", null, true);
                    echo "\">
                    <label for=\"y\">";
                    // line 76
                    echo _gettext("Y");
                    echo "</label>
                    <input name=\"gis_data[";
                    // line 77
                    echo twig_escape_filter($this->env, $context["a"], "html", null, true);
                    echo "][POINT][y]\" type=\"text\" value=\"";
                    echo twig_escape_filter($this->env, (($__internal_887a873a4dc3cf8bd4f99c487b4c7727999c350cc3a772414714e49a195e4386 = (($__internal_d527c24a729d38501d770b40a0d25e1ce8a7f0bff897cc4f8f449ba71fcff3d9 = (($__internal_f6dde3a1020453fdf35e718e94f93ce8eb8803b28cc77a665308e14bbe8572ae = ($context["gis_data"] ?? null)) && is_array($__internal_f6dde3a1020453fdf35e718e94f93ce8eb8803b28cc77a665308e14bbe8572ae) || $__internal_f6dde3a1020453fdf35e718e94f93ce8eb8803b28cc77a665308e14bbe8572ae instanceof ArrayAccess ? ($__internal_f6dde3a1020453fdf35e718e94f93ce8eb8803b28cc77a665308e14bbe8572ae[$context["a"]] ?? null) : null)) && is_array($__internal_d527c24a729d38501d770b40a0d25e1ce8a7f0bff897cc4f8f449ba71fcff3d9) || $__internal_d527c24a729d38501d770b40a0d25e1ce8a7f0bff897cc4f8f449ba71fcff3d9 instanceof ArrayAccess ? ($__internal_d527c24a729d38501d770b40a0d25e1ce8a7f0bff897cc4f8f449ba71fcff3d9["POINT"] ?? null) : null)) && is_array($__internal_887a873a4dc3cf8bd4f99c487b4c7727999c350cc3a772414714e49a195e4386) || $__internal_887a873a4dc3cf8bd4f99c487b4c7727999c350cc3a772414714e49a195e4386 instanceof ArrayAccess ? ($__internal_887a873a4dc3cf8bd4f99c487b4c7727999c350cc3a772414714e49a195e4386["y"] ?? null) : null), "html", null, true);
                    echo "\">
                ";
                } elseif (((                // line 78
($context["type"] ?? null) == "MULTIPOINT") || (($context["type"] ?? null) == "LINESTRING"))) {
                    // line 79
                    echo "                    ";
                    $context["no_of_points"] = (((twig_get_attribute($this->env, $this->source, twig_get_attribute($this->env, $this->source, twig_get_attribute($this->env, $this->source, ($context["gis_data"] ?? null), $context["a"], [], "array", false, true, false, 79), ($context["type"] ?? null), [], "array", false, true, false, 79), "no_of_points", [], "array", true, true, false, 79) &&  !(null === (($__internal_25c0fab8152b8dd6b90603159c0f2e8a936a09ab76edb5e4d7bc95d9a8d2dc8f = twig_get_attribute($this->env, $this->source, twig_get_attribute($this->env, $this->source, ($context["gis_data"] ?? null), $context["a"], [], "array", false, true, false, 79), ($context["type"] ?? null), [], "array", false, true, false, 79)) && is_array($__internal_25c0fab8152b8dd6b90603159c0f2e8a936a09ab76edb5e4d7bc95d9a8d2dc8f) || $__internal_25c0fab8152b8dd6b90603159c0f2e8a936a09ab76edb5e4d7bc95d9a8d2dc8f instanceof ArrayAccess ? ($__internal_25c0fab8152b8dd6b90603159c0f2e8a936a09ab76edb5e4d7bc95d9a8d2dc8f["no_of_points"] ?? null) : null)))) ? ((($__internal_f769f712f3484f00110c86425acea59f5af2752239e2e8596bcb6effeb425b40 = twig_get_attribute($this->env, $this->source, twig_get_attribute($this->env, $this->source, ($context["gis_data"] ?? null), $context["a"], [], "array", false, true, false, 79), ($context["type"] ?? null), [], "array", false, true, false, 79)) && is_array($__internal_f769f712f3484f00110c86425acea59f5af2752239e2e8596bcb6effeb425b40) || $__internal_f769f712f3484f00110c86425acea59f5af2752239e2e8596bcb6effeb425b40 instanceof ArrayAccess ? ($__internal_f769f712f3484f00110c86425acea59f5af2752239e2e8596bcb6effeb425b40["no_of_points"] ?? null) : null)) : (1));
                    // line 80
                    echo "                    ";
                    if (((($context["type"] ?? null) == "LINESTRING") && (($context["no_of_points"] ?? null) < 2))) {
                        // line 81
                        echo "                        ";
                        $context["no_of_points"] = 2;
                        // line 82
                        echo "                    ";
                    }
                    // line 83
                    echo "                    ";
                    if (((($context["type"] ?? null) == "MULTIPOINT") && (($context["no_of_points"] ?? null) < 1))) {
                        // line 84
                        echo "                        ";
                        $context["no_of_points"] = 1;
                        // line 85
                        echo "                    ";
                    }
                    // line 86
                    echo "                    ";
                    if ( !(null === (($__internal_98e944456c0f58b2585e4aa36e3a7e43f4b7c9038088f0f056004af41f4a007f = (($__internal_a06a70691a7ca361709a372174fa669f5ee1c1e4ed302b3a5b61c10c80c02760 = (($__internal_653499042eb14fd8415489ba6fa87c1e85cff03392e9f57b26d0da09b9be82ce = ($context["gis_data"] ?? null)) && is_array($__internal_653499042eb14fd8415489ba6fa87c1e85cff03392e9f57b26d0da09b9be82ce) || $__internal_653499042eb14fd8415489ba6fa87c1e85cff03392e9f57b26d0da09b9be82ce instanceof ArrayAccess ? ($__internal_653499042eb14fd8415489ba6fa87c1e85cff03392e9f57b26d0da09b9be82ce[$context["a"]] ?? null) : null)) && is_array($__internal_a06a70691a7ca361709a372174fa669f5ee1c1e4ed302b3a5b61c10c80c02760) || $__internal_a06a70691a7ca361709a372174fa669f5ee1c1e4ed302b3a5b61c10c80c02760 instanceof ArrayAccess ? ($__internal_a06a70691a7ca361709a372174fa669f5ee1c1e4ed302b3a5b61c10c80c02760[($context["type"] ?? null)] ?? null) : null)) && is_array($__internal_98e944456c0f58b2585e4aa36e3a7e43f4b7c9038088f0f056004af41f4a007f) || $__internal_98e944456c0f58b2585e4aa36e3a7e43f4b7c9038088f0f056004af41f4a007f instanceof ArrayAccess ? ($__internal_98e944456c0f58b2585e4aa36e3a7e43f4b7c9038088f0f056004af41f4a007f["add_point"] ?? null) : null))) {
                        // line 87
                        echo "                        ";
                        $context["no_of_points"] = (($context["no_of_points"] ?? null) + 1);
                        // line 88
                        echo "                    ";
                    }
                    // line 89
                    echo "                    <input type=\"hidden\" value=\"";
                    echo twig_escape_filter($this->env, ($context["no_of_points"] ?? null), "html", null, true);
                    echo "\" name=\"gis_data[";
                    echo twig_escape_filter($this->env, $context["a"], "html", null, true);
                    echo "][";
                    echo twig_escape_filter($this->env, ($context["type"] ?? null), "html", null, true);
                    echo "][no_of_points]\">

                    ";
                    // line 91
                    $context['_parent'] = $context;
                    $context['_seq'] = twig_ensure_traversable(range(0, (($context["no_of_points"] ?? null) - 1)));
                    foreach ($context['_seq'] as $context["_key"] => $context["i"]) {
                        // line 92
                        echo "                        <br>
                        ";
                        // line 93
                        echo twig_escape_filter($this->env, sprintf(_gettext("Point %d:"), ($context["i"] + 1)), "html", null, true);
                        echo "
                        <label for=\"x\">";
                        // line 94
                        echo _gettext("X");
                        echo "</label>
                        <input type=\"text\" name=\"gis_data[";
                        // line 95
                        echo twig_escape_filter($this->env, $context["a"], "html", null, true);
                        echo "][";
                        echo twig_escape_filter($this->env, ($context["type"] ?? null), "html", null, true);
                        echo "][";
                        echo twig_escape_filter($this->env, $context["i"], "html", null, true);
                        echo "][x]\" value=\"";
                        echo twig_escape_filter($this->env, (($__internal_ba9f0a3bb95c082f61c9fbf892a05514d732703d52edc77b51f2e6284135900b = (($__internal_73db8eef4d2582468dab79a6b09c77ce3b48675a610afd65a1f325b68804a60c = (($__internal_d8ad5934f1874c52fa2ac9a4dfae52038b39b8b03cfc82eeb53de6151d883972 = (($__internal_df39c71428eaf37baa1ea2198679e0077f3699bdd31bb5ba10d084710b9da216 = ($context["gis_data"] ?? null)) && is_array($__internal_df39c71428eaf37baa1ea2198679e0077f3699bdd31bb5ba10d084710b9da216) || $__internal_df39c71428eaf37baa1ea2198679e0077f3699bdd31bb5ba10d084710b9da216 instanceof ArrayAccess ? ($__internal_df39c71428eaf37baa1ea2198679e0077f3699bdd31bb5ba10d084710b9da216[$context["a"]] ?? null) : null)) && is_array($__internal_d8ad5934f1874c52fa2ac9a4dfae52038b39b8b03cfc82eeb53de6151d883972) || $__internal_d8ad5934f1874c52fa2ac9a4dfae52038b39b8b03cfc82eeb53de6151d883972 instanceof ArrayAccess ? ($__internal_d8ad5934f1874c52fa2ac9a4dfae52038b39b8b03cfc82eeb53de6151d883972[($context["type"] ?? null)] ?? null) : null)) && is_array($__internal_73db8eef4d2582468dab79a6b09c77ce3b48675a610afd65a1f325b68804a60c) || $__internal_73db8eef4d2582468dab79a6b09c77ce3b48675a610afd65a1f325b68804a60c instanceof ArrayAccess ? ($__internal_73db8eef4d2582468dab79a6b09c77ce3b48675a610afd65a1f325b68804a60c[$context["i"]] ?? null) : null)) && is_array($__internal_ba9f0a3bb95c082f61c9fbf892a05514d732703d52edc77b51f2e6284135900b) || $__internal_ba9f0a3bb95c082f61c9fbf892a05514d732703d52edc77b51f2e6284135900b instanceof ArrayAccess ? ($__internal_ba9f0a3bb95c082f61c9fbf892a05514d732703d52edc77b51f2e6284135900b["x"] ?? null) : null), "html", null, true);
                        echo "\">
                        <label for=\"y\">";
                        // line 96
                        echo _gettext("Y");
                        echo "</label>
                        <input type=\"text\" name=\"gis_data[";
                        // line 97
                        echo twig_escape_filter($this->env, $context["a"], "html", null, true);
                        echo "][";
                        echo twig_escape_filter($this->env, ($context["type"] ?? null), "html", null, true);
                        echo "][";
                        echo twig_escape_filter($this->env, $context["i"], "html", null, true);
                        echo "][y]\" value=\"";
                        echo twig_escape_filter($this->env, (($__internal_bf0e189d688dc2ad611b50a437a32d3692fb6b8be90d2228617cfa6db44e75c0 = (($__internal_674c0abf302105af78b0a38907d86c5dd0028bdc3ee5f24bf52771a16487760c = (($__internal_dd839fbfcab68823c49af471c7df7659a500fe72e71b58d6b80d896bdb55e75f = (($__internal_a7ed47878554bdc32b70e1ba5ccc67d2302196876fbf62b4c853b20cb9e029fc = ($context["gis_data"] ?? null)) && is_array($__internal_a7ed47878554bdc32b70e1ba5ccc67d2302196876fbf62b4c853b20cb9e029fc) || $__internal_a7ed47878554bdc32b70e1ba5ccc67d2302196876fbf62b4c853b20cb9e029fc instanceof ArrayAccess ? ($__internal_a7ed47878554bdc32b70e1ba5ccc67d2302196876fbf62b4c853b20cb9e029fc[$context["a"]] ?? null) : null)) && is_array($__internal_dd839fbfcab68823c49af471c7df7659a500fe72e71b58d6b80d896bdb55e75f) || $__internal_dd839fbfcab68823c49af471c7df7659a500fe72e71b58d6b80d896bdb55e75f instanceof ArrayAccess ? ($__internal_dd839fbfcab68823c49af471c7df7659a500fe72e71b58d6b80d896bdb55e75f[($context["type"] ?? null)] ?? null) : null)) && is_array($__internal_674c0abf302105af78b0a38907d86c5dd0028bdc3ee5f24bf52771a16487760c) || $__internal_674c0abf302105af78b0a38907d86c5dd0028bdc3ee5f24bf52771a16487760c instanceof ArrayAccess ? ($__internal_674c0abf302105af78b0a38907d86c5dd0028bdc3ee5f24bf52771a16487760c[$context["i"]] ?? null) : null)) && is_array($__internal_bf0e189d688dc2ad611b50a437a32d3692fb6b8be90d2228617cfa6db44e75c0) || $__internal_bf0e189d688dc2ad611b50a437a32d3692fb6b8be90d2228617cfa6db44e75c0 instanceof ArrayAccess ? ($__internal_bf0e189d688dc2ad611b50a437a32d3692fb6b8be90d2228617cfa6db44e75c0["y"] ?? null) : null), "html", null, true);
                        echo "\">
                    ";
                    }
                    $_parent = $context['_parent'];
                    unset($context['_seq'], $context['_iterated'], $context['_key'], $context['i'], $context['_parent'], $context['loop']);
                    $context = array_intersect_key($context, $_parent) + $_parent;
                    // line 99
                    echo "                    <input type=\"submit\" name=\"gis_data[";
                    echo twig_escape_filter($this->env, $context["a"], "html", null, true);
                    echo "][";
                    echo twig_escape_filter($this->env, ($context["type"] ?? null), "html", null, true);
                    echo "][add_point]\" class=\"btn btn-secondary add addPoint\" value=\"";
                    echo _gettext("Add a point");
                    echo "\">
                ";
                } elseif (((                // line 100
($context["type"] ?? null) == "MULTILINESTRING") || (($context["type"] ?? null) == "POLYGON"))) {
                    // line 101
                    echo "                    ";
                    $context["no_of_lines"] = (((twig_get_attribute($this->env, $this->source, twig_get_attribute($this->env, $this->source, twig_get_attribute($this->env, $this->source, ($context["gis_data"] ?? null), $context["a"], [], "array", false, true, false, 101), ($context["type"] ?? null), [], "array", false, true, false, 101), "no_of_lines", [], "array", true, true, false, 101) &&  !(null === (($__internal_e5d7b41e16b744b68da1e9bb49047b8028ced86c782900009b4b4029b83d4b55 = twig_get_attribute($this->env, $this->source, twig_get_attribute($this->env, $this->source, ($context["gis_data"] ?? null), $context["a"], [], "array", false, true, false, 101), ($context["type"] ?? null), [], "array", false, true, false, 101)) && is_array($__internal_e5d7b41e16b744b68da1e9bb49047b8028ced86c782900009b4b4029b83d4b55) || $__internal_e5d7b41e16b744b68da1e9bb49047b8028ced86c782900009b4b4029b83d4b55 instanceof ArrayAccess ? ($__internal_e5d7b41e16b744b68da1e9bb49047b8028ced86c782900009b4b4029b83d4b55["no_of_lines"] ?? null) : null)))) ? ((($__internal_9e93f398968fa0576dce82fd00f280e95c734ad3f84e7816ff09158ae224f5ba = twig_get_attribute($this->env, $this->source, twig_get_attribute($this->env, $this->source, ($context["gis_data"] ?? null), $context["a"], [], "array", false, true, false, 101), ($context["type"] ?? null), [], "array", false, true, false, 101)) && is_array($__internal_9e93f398968fa0576dce82fd00f280e95c734ad3f84e7816ff09158ae224f5ba) || $__internal_9e93f398968fa0576dce82fd00f280e95c734ad3f84e7816ff09158ae224f5ba instanceof ArrayAccess ? ($__internal_9e93f398968fa0576dce82fd00f280e95c734ad3f84e7816ff09158ae224f5ba["no_of_lines"] ?? null) : null)) : (1));
                    // line 102
                    echo "                    ";
                    if ((($context["no_of_lines"] ?? null) < 1)) {
                        // line 103
                        echo "                        ";
                        $context["no_of_lines"] = 1;
                        // line 104
                        echo "                    ";
                    }
                    // line 105
                    echo "                    ";
                    if ( !(null === (($__internal_0795e3de58b6454b051261c0c2b5be48852e17f25b59d4aeef29fb07c614bd78 = (($__internal_fecb0565c93d0b979a95c352ff76e401e0ae0c73bb8d3b443c8c6133e1c190de = (($__internal_87570a635eac7f6e150744bd218085d17aff15d92d9c80a66d3b911e3355b828 = ($context["gis_data"] ?? null)) && is_array($__internal_87570a635eac7f6e150744bd218085d17aff15d92d9c80a66d3b911e3355b828) || $__internal_87570a635eac7f6e150744bd218085d17aff15d92d9c80a66d3b911e3355b828 instanceof ArrayAccess ? ($__internal_87570a635eac7f6e150744bd218085d17aff15d92d9c80a66d3b911e3355b828[$context["a"]] ?? null) : null)) && is_array($__internal_fecb0565c93d0b979a95c352ff76e401e0ae0c73bb8d3b443c8c6133e1c190de) || $__internal_fecb0565c93d0b979a95c352ff76e401e0ae0c73bb8d3b443c8c6133e1c190de instanceof ArrayAccess ? ($__internal_fecb0565c93d0b979a95c352ff76e401e0ae0c73bb8d3b443c8c6133e1c190de[($context["type"] ?? null)] ?? null) : null)) && is_array($__internal_0795e3de58b6454b051261c0c2b5be48852e17f25b59d4aeef29fb07c614bd78) || $__internal_0795e3de58b6454b051261c0c2b5be48852e17f25b59d4aeef29fb07c614bd78 instanceof ArrayAccess ? ($__internal_0795e3de58b6454b051261c0c2b5be48852e17f25b59d4aeef29fb07c614bd78["add_line"] ?? null) : null))) {
                        // line 106
                        echo "                        ";
                        $context["no_of_lines"] = (($context["no_of_lines"] ?? null) + 1);
                        // line 107
                        echo "                    ";
                    }
                    // line 108
                    echo "                    <input type=\"hidden\" value=\"";
                    echo twig_escape_filter($this->env, ($context["no_of_lines"] ?? null), "html", null, true);
                    echo "\" name=\"gis_data[";
                    echo twig_escape_filter($this->env, $context["a"], "html", null, true);
                    echo "][";
                    echo twig_escape_filter($this->env, ($context["type"] ?? null), "html", null, true);
                    echo "][no_of_lines]\">

                    ";
                    // line 110
                    $context['_parent'] = $context;
                    $context['_seq'] = twig_ensure_traversable(range(0, (($context["no_of_lines"] ?? null) - 1)));
                    foreach ($context['_seq'] as $context["_key"] => $context["i"]) {
                        // line 111
                        echo "                        <br>
                        ";
                        // line 112
                        if ((($context["type"] ?? null) == "MULTILINESTRING")) {
                            // line 113
                            echo "                            ";
                            echo twig_escape_filter($this->env, sprintf(_gettext("Linestring %d:"), ($context["i"] + 1)), "html", null, true);
                            echo "
                        ";
                        } elseif ((                        // line 114
$context["i"] == 0)) {
                            // line 115
                            echo "                            ";
                            echo _gettext("Outer ring:");
                            // line 116
                            echo "                        ";
                        } else {
                            // line 117
                            echo "                            ";
                            echo twig_escape_filter($this->env, sprintf(_gettext("Inner ring %d:"), $context["i"]), "html", null, true);
                            echo "
                        ";
                        }
                        // line 119
                        echo "
                        ";
                        // line 120
                        $context["no_of_points"] = (((twig_get_attribute($this->env, $this->source, twig_get_attribute($this->env, $this->source, twig_get_attribute($this->env, $this->source, twig_get_attribute($this->env, $this->source, ($context["gis_data"] ?? null), $context["a"], [], "array", false, true, false, 120), ($context["type"] ?? null), [], "array", false, true, false, 120), $context["i"], [], "array", false, true, false, 120), "no_of_points", [], "array", true, true, false, 120) &&  !(null === (($__internal_17b5b5f9aaeec4b528bfeed02b71f624021d6a52d927f441de2f2204d0e527cd = twig_get_attribute($this->env, $this->source, twig_get_attribute($this->env, $this->source, twig_get_attribute($this->env, $this->source, ($context["gis_data"] ?? null), $context["a"], [], "array", false, true, false, 120), ($context["type"] ?? null), [], "array", false, true, false, 120), $context["i"], [], "array", false, true, false, 120)) && is_array($__internal_17b5b5f9aaeec4b528bfeed02b71f624021d6a52d927f441de2f2204d0e527cd) || $__internal_17b5b5f9aaeec4b528bfeed02b71f624021d6a52d927f441de2f2204d0e527cd instanceof ArrayAccess ? ($__internal_17b5b5f9aaeec4b528bfeed02b71f624021d6a52d927f441de2f2204d0e527cd["no_of_points"] ?? null) : null)))) ? ((($__internal_0db9a23306660395861a0528381e0668025e56a8a99f399e9ec23a4b392422d6 = twig_get_attribute($this->env, $this->source, twig_get_attribute($this->env, $this->source, twig_get_attribute($this->env, $this->source, ($context["gis_data"] ?? null), $context["a"], [], "array", false, true, false, 120), ($context["type"] ?? null), [], "array", false, true, false, 120), $context["i"], [], "array", false, true, false, 120)) && is_array($__internal_0db9a23306660395861a0528381e0668025e56a8a99f399e9ec23a4b392422d6) || $__internal_0db9a23306660395861a0528381e0668025e56a8a99f399e9ec23a4b392422d6 instanceof ArrayAccess ? ($__internal_0db9a23306660395861a0528381e0668025e56a8a99f399e9ec23a4b392422d6["no_of_points"] ?? null) : null)) : (2));
                        // line 121
                        echo "                        ";
                        if (((($context["type"] ?? null) == "MULTILINESTRING") && (($context["no_of_points"] ?? null) < 2))) {
                            // line 122
                            echo "                            ";
                            $context["no_of_points"] = 2;
                            // line 123
                            echo "                        ";
                        }
                        // line 124
                        echo "                        ";
                        if (((($context["type"] ?? null) == "POLYGON") && (($context["no_of_points"] ?? null) < 4))) {
                            // line 125
                            echo "                            ";
                            $context["no_of_points"] = 4;
                            // line 126
                            echo "                        ";
                        }
                        // line 127
                        echo "                        ";
                        if ( !(null === (($__internal_0a23ad2f11a348e49c87410947e20d5a4e711234ce49927662da5dddac687855 = (($__internal_0228c5445a74540c89ea8a758478d405796357800f8af831a7f7e1e2c0159d9b = (($__internal_6fb04c4457ec9ffa7dd6fd2300542be8b961b6e5f7858a80a282f47b43ddae5f = (($__internal_417a1a95b289c75779f33186a6dc0b71d01f257b68beae7dcb9d2d769acca0e0 = ($context["gis_data"] ?? null)) && is_array($__internal_417a1a95b289c75779f33186a6dc0b71d01f257b68beae7dcb9d2d769acca0e0) || $__internal_417a1a95b289c75779f33186a6dc0b71d01f257b68beae7dcb9d2d769acca0e0 instanceof ArrayAccess ? ($__internal_417a1a95b289c75779f33186a6dc0b71d01f257b68beae7dcb9d2d769acca0e0[$context["a"]] ?? null) : null)) && is_array($__internal_6fb04c4457ec9ffa7dd6fd2300542be8b961b6e5f7858a80a282f47b43ddae5f) || $__internal_6fb04c4457ec9ffa7dd6fd2300542be8b961b6e5f7858a80a282f47b43ddae5f instanceof ArrayAccess ? ($__internal_6fb04c4457ec9ffa7dd6fd2300542be8b961b6e5f7858a80a282f47b43ddae5f[($context["type"] ?? null)] ?? null) : null)) && is_array($__internal_0228c5445a74540c89ea8a758478d405796357800f8af831a7f7e1e2c0159d9b) || $__internal_0228c5445a74540c89ea8a758478d405796357800f8af831a7f7e1e2c0159d9b instanceof ArrayAccess ? ($__internal_0228c5445a74540c89ea8a758478d405796357800f8af831a7f7e1e2c0159d9b[$context["i"]] ?? null) : null)) && is_array($__internal_0a23ad2f11a348e49c87410947e20d5a4e711234ce49927662da5dddac687855) || $__internal_0a23ad2f11a348e49c87410947e20d5a4e711234ce49927662da5dddac687855 instanceof ArrayAccess ? ($__internal_0a23ad2f11a348e49c87410947e20d5a4e711234ce49927662da5dddac687855["add_point"] ?? null) : null))) {
                            // line 128
                            echo "                            ";
                            $context["no_of_points"] = (($context["no_of_points"] ?? null) + 1);
                            // line 129
                            echo "                        ";
                        }
                        // line 130
                        echo "                        <input type=\"hidden\" value=\"";
                        echo twig_escape_filter($this->env, ($context["no_of_points"] ?? null), "html", null, true);
                        echo "\" name=\"gis_data[";
                        echo twig_escape_filter($this->env, $context["a"], "html", null, true);
                        echo "][";
                        echo twig_escape_filter($this->env, ($context["type"] ?? null), "html", null, true);
                        echo "][";
                        echo twig_escape_filter($this->env, $context["i"], "html", null, true);
                        echo "][no_of_points]\">

                        ";
                        // line 132
                        $context['_parent'] = $context;
                        $context['_seq'] = twig_ensure_traversable(range(0, (($context["no_of_points"] ?? null) - 1)));
                        foreach ($context['_seq'] as $context["_key"] => $context["j"]) {
                            // line 133
                            echo "                            <br>
                            ";
                            // line 134
                            echo twig_escape_filter($this->env, sprintf(_gettext("Point %d:"), ($context["j"] + 1)), "html", null, true);
                            echo "
                            <label for=\"x\">";
                            // line 135
                            echo _gettext("X");
                            echo "</label>
                            <input type=\"text\" name=\"gis_data[";
                            // line 136
                            echo twig_escape_filter($this->env, $context["a"], "html", null, true);
                            echo "][";
                            echo twig_escape_filter($this->env, ($context["type"] ?? null), "html", null, true);
                            echo "][";
                            echo twig_escape_filter($this->env, $context["i"], "html", null, true);
                            echo "][";
                            echo twig_escape_filter($this->env, $context["j"], "html", null, true);
                            echo "][x]\" value=\"";
                            echo twig_escape_filter($this->env, (($__internal_af3439635eb343262861f05093b3dcce5d4dae1e20a47bc25a2eef28135b0d55 = (($__internal_b16f7904bcaaa7a87404cbf85addc7a8645dff94eef4e8ae7182b86e3638e76a = (($__internal_462377748602ccf3a44a10ced4240983cec8df1ad86ab53e582fcddddb98fc88 = (($__internal_be1db6a1ea9fa5c04c40f99df0ec5af053ca391863fc6256c5c4ee249724f758 = (($__internal_6e6eda1691934a8f5855a3221f5a9f036391304a5cda73a3a2009f2961a84c35 = ($context["gis_data"] ?? null)) && is_array($__internal_6e6eda1691934a8f5855a3221f5a9f036391304a5cda73a3a2009f2961a84c35) || $__internal_6e6eda1691934a8f5855a3221f5a9f036391304a5cda73a3a2009f2961a84c35 instanceof ArrayAccess ? ($__internal_6e6eda1691934a8f5855a3221f5a9f036391304a5cda73a3a2009f2961a84c35[$context["a"]] ?? null) : null)) && is_array($__internal_be1db6a1ea9fa5c04c40f99df0ec5af053ca391863fc6256c5c4ee249724f758) || $__internal_be1db6a1ea9fa5c04c40f99df0ec5af053ca391863fc6256c5c4ee249724f758 instanceof ArrayAccess ? ($__internal_be1db6a1ea9fa5c04c40f99df0ec5af053ca391863fc6256c5c4ee249724f758[($context["type"] ?? null)] ?? null) : null)) && is_array($__internal_462377748602ccf3a44a10ced4240983cec8df1ad86ab53e582fcddddb98fc88) || $__internal_462377748602ccf3a44a10ced4240983cec8df1ad86ab53e582fcddddb98fc88 instanceof ArrayAccess ? ($__internal_462377748602ccf3a44a10ced4240983cec8df1ad86ab53e582fcddddb98fc88[$context["i"]] ?? null) : null)) && is_array($__internal_b16f7904bcaaa7a87404cbf85addc7a8645dff94eef4e8ae7182b86e3638e76a) || $__internal_b16f7904bcaaa7a87404cbf85addc7a8645dff94eef4e8ae7182b86e3638e76a instanceof ArrayAccess ? ($__internal_b16f7904bcaaa7a87404cbf85addc7a8645dff94eef4e8ae7182b86e3638e76a[$context["j"]] ?? null) : null)) && is_array($__internal_af3439635eb343262861f05093b3dcce5d4dae1e20a47bc25a2eef28135b0d55) || $__internal_af3439635eb343262861f05093b3dcce5d4dae1e20a47bc25a2eef28135b0d55 instanceof ArrayAccess ? ($__internal_af3439635eb343262861f05093b3dcce5d4dae1e20a47bc25a2eef28135b0d55["x"] ?? null) : null), "html", null, true);
                            echo "\">
                            <label for=\"y\">";
                            // line 137
                            echo _gettext("Y");
                            echo "</label>
                            <input type=\"text\" name=\"gis_data[";
                            // line 138
                            echo twig_escape_filter($this->env, $context["a"], "html", null, true);
                            echo "][";
                            echo twig_escape_filter($this->env, ($context["type"] ?? null), "html", null, true);
                            echo "][";
                            echo twig_escape_filter($this->env, $context["i"], "html", null, true);
                            echo "][";
                            echo twig_escape_filter($this->env, $context["j"], "html", null, true);
                            echo "][y]\" value=\"";
                            echo twig_escape_filter($this->env, (($__internal_51c633083c79004f3cb5e9e2b2f3504f650f1561800582801028bcbcf733a06b = (($__internal_064553f1273f2ea50405f85092d06733f3f2fe5d0fc42fda135e1fdc91ff26ae = (($__internal_7bef02f75e2984f8c7fcd4fd7871e286c87c0fdcb248271a136b01ac6dd5dd54 = (($__internal_d6ae6b41786cc4be7778386d06cb288c8e6ffd74e055cfed45d7a5c8854d0c8f = (($__internal_1dcdec7ec31e102fbfe45103ea3599c92c8609311e43d40ca0d95d0369434327 = ($context["gis_data"] ?? null)) && is_array($__internal_1dcdec7ec31e102fbfe45103ea3599c92c8609311e43d40ca0d95d0369434327) || $__internal_1dcdec7ec31e102fbfe45103ea3599c92c8609311e43d40ca0d95d0369434327 instanceof ArrayAccess ? ($__internal_1dcdec7ec31e102fbfe45103ea3599c92c8609311e43d40ca0d95d0369434327[$context["a"]] ?? null) : null)) && is_array($__internal_d6ae6b41786cc4be7778386d06cb288c8e6ffd74e055cfed45d7a5c8854d0c8f) || $__internal_d6ae6b41786cc4be7778386d06cb288c8e6ffd74e055cfed45d7a5c8854d0c8f instanceof ArrayAccess ? ($__internal_d6ae6b41786cc4be7778386d06cb288c8e6ffd74e055cfed45d7a5c8854d0c8f[($context["type"] ?? null)] ?? null) : null)) && is_array($__internal_7bef02f75e2984f8c7fcd4fd7871e286c87c0fdcb248271a136b01ac6dd5dd54) || $__internal_7bef02f75e2984f8c7fcd4fd7871e286c87c0fdcb248271a136b01ac6dd5dd54 instanceof ArrayAccess ? ($__internal_7bef02f75e2984f8c7fcd4fd7871e286c87c0fdcb248271a136b01ac6dd5dd54[$context["i"]] ?? null) : null)) && is_array($__internal_064553f1273f2ea50405f85092d06733f3f2fe5d0fc42fda135e1fdc91ff26ae) || $__internal_064553f1273f2ea50405f85092d06733f3f2fe5d0fc42fda135e1fdc91ff26ae instanceof ArrayAccess ? ($__internal_064553f1273f2ea50405f85092d06733f3f2fe5d0fc42fda135e1fdc91ff26ae[$context["j"]] ?? null) : null)) && is_array($__internal_51c633083c79004f3cb5e9e2b2f3504f650f1561800582801028bcbcf733a06b) || $__internal_51c633083c79004f3cb5e9e2b2f3504f650f1561800582801028bcbcf733a06b instanceof ArrayAccess ? ($__internal_51c633083c79004f3cb5e9e2b2f3504f650f1561800582801028bcbcf733a06b["y"] ?? null) : null), "html", null, true);
                            echo "\">
                        ";
                        }
                        $_parent = $context['_parent'];
                        unset($context['_seq'], $context['_iterated'], $context['_key'], $context['j'], $context['_parent'], $context['loop']);
                        $context = array_intersect_key($context, $_parent) + $_parent;
                        // line 140
                        echo "                        <input type=\"submit\" name=\"gis_data[";
                        echo twig_escape_filter($this->env, $context["a"], "html", null, true);
                        echo "][";
                        echo twig_escape_filter($this->env, ($context["type"] ?? null), "html", null, true);
                        echo "][";
                        echo twig_escape_filter($this->env, $context["i"], "html", null, true);
                        echo "][add_point]\" class=\"btn btn-secondary add addPoint\" value=\"";
                        echo _gettext("Add a point");
                        echo "\">
                    ";
                    }
                    $_parent = $context['_parent'];
                    unset($context['_seq'], $context['_iterated'], $context['_key'], $context['i'], $context['_parent'], $context['loop']);
                    $context = array_intersect_key($context, $_parent) + $_parent;
                    // line 142
                    echo "                    <br>
                    <input type=\"submit\" name=\"gis_data[";
                    // line 143
                    echo twig_escape_filter($this->env, $context["a"], "html", null, true);
                    echo "][";
                    echo twig_escape_filter($this->env, ($context["type"] ?? null), "html", null, true);
                    echo "][add_line]\" class=\"btn btn-secondary add addLine\" value=\"";
                    // line 144
                    echo twig_escape_filter($this->env, (((($context["type"] ?? null) == "MULTILINESTRING")) ? (_gettext("Add a linestring")) : (_gettext("Add an inner ring"))), "html", null, true);
                    echo "\">
                ";
                } elseif ((                // line 145
($context["type"] ?? null) == "MULTIPOLYGON")) {
                    // line 146
                    echo "                    ";
                    $context["no_of_polygons"] = (((twig_get_attribute($this->env, $this->source, twig_get_attribute($this->env, $this->source, twig_get_attribute($this->env, $this->source, ($context["gis_data"] ?? null), $context["a"], [], "array", false, true, false, 146), ($context["type"] ?? null), [], "array", false, true, false, 146), "no_of_polygons", [], "array", true, true, false, 146) &&  !(null === (($__internal_891ba2f942018e94e4bfa8069988f305bbaad7f54a64aeee069787f1084a9412 = twig_get_attribute($this->env, $this->source, twig_get_attribute($this->env, $this->source, ($context["gis_data"] ?? null), $context["a"], [], "array", false, true, false, 146), ($context["type"] ?? null), [], "array", false, true, false, 146)) && is_array($__internal_891ba2f942018e94e4bfa8069988f305bbaad7f54a64aeee069787f1084a9412) || $__internal_891ba2f942018e94e4bfa8069988f305bbaad7f54a64aeee069787f1084a9412 instanceof ArrayAccess ? ($__internal_891ba2f942018e94e4bfa8069988f305bbaad7f54a64aeee069787f1084a9412["no_of_polygons"] ?? null) : null)))) ? ((($__internal_694b5f53081640f33aab1567e85e28c247e6a7c4674010716df6de8eae4819e9 = twig_get_attribute($this->env, $this->source, twig_get_attribute($this->env, $this->source, ($context["gis_data"] ?? null), $context["a"], [], "array", false, true, false, 146), ($context["type"] ?? null), [], "array", false, true, false, 146)) && is_array($__internal_694b5f53081640f33aab1567e85e28c247e6a7c4674010716df6de8eae4819e9) || $__internal_694b5f53081640f33aab1567e85e28c247e6a7c4674010716df6de8eae4819e9 instanceof ArrayAccess ? ($__internal_694b5f53081640f33aab1567e85e28c247e6a7c4674010716df6de8eae4819e9["no_of_polygons"] ?? null) : null)) : (1));
                    // line 147
                    echo "                    ";
                    if ((($context["no_of_polygons"] ?? null) < 1)) {
                        // line 148
                        echo "                        ";
                        $context["no_of_polygons"] = 1;
                        // line 149
                        echo "                    ";
                    }
                    // line 150
                    echo "                    ";
                    if ( !(null === (($__internal_91b272a21580197773f482962c8b92637a641a749832ee390d7d386a58d1912e = (($__internal_7f8d0071642f16d6b4720f8eef58ffd71faf0c4d7a772c0eb6842d5e9d901ca5 = (($__internal_0aa0713b35e28227396d65db75a1a4277b081ff4e08585143330919af9d1bf0a = ($context["gis_data"] ?? null)) && is_array($__internal_0aa0713b35e28227396d65db75a1a4277b081ff4e08585143330919af9d1bf0a) || $__internal_0aa0713b35e28227396d65db75a1a4277b081ff4e08585143330919af9d1bf0a instanceof ArrayAccess ? ($__internal_0aa0713b35e28227396d65db75a1a4277b081ff4e08585143330919af9d1bf0a[$context["a"]] ?? null) : null)) && is_array($__internal_7f8d0071642f16d6b4720f8eef58ffd71faf0c4d7a772c0eb6842d5e9d901ca5) || $__internal_7f8d0071642f16d6b4720f8eef58ffd71faf0c4d7a772c0eb6842d5e9d901ca5 instanceof ArrayAccess ? ($__internal_7f8d0071642f16d6b4720f8eef58ffd71faf0c4d7a772c0eb6842d5e9d901ca5[($context["type"] ?? null)] ?? null) : null)) && is_array($__internal_91b272a21580197773f482962c8b92637a641a749832ee390d7d386a58d1912e) || $__internal_91b272a21580197773f482962c8b92637a641a749832ee390d7d386a58d1912e instanceof ArrayAccess ? ($__internal_91b272a21580197773f482962c8b92637a641a749832ee390d7d386a58d1912e["add_polygon"] ?? null) : null))) {
                        // line 151
                        echo "                        ";
                        $context["no_of_polygons"] = (($context["no_of_polygons"] ?? null) + 1);
                        // line 152
                        echo "                    ";
                    }
                    // line 153
                    echo "                    <input type=\"hidden\" name=\"gis_data[";
                    echo twig_escape_filter($this->env, $context["a"], "html", null, true);
                    echo "][";
                    echo twig_escape_filter($this->env, ($context["type"] ?? null), "html", null, true);
                    echo "][no_of_polygons]\" value=\"";
                    echo twig_escape_filter($this->env, ($context["no_of_polygons"] ?? null), "html", null, true);
                    echo "\">

                    ";
                    // line 155
                    $context['_parent'] = $context;
                    $context['_seq'] = twig_ensure_traversable(range(0, (($context["no_of_polygons"] ?? null) - 1)));
                    foreach ($context['_seq'] as $context["_key"] => $context["k"]) {
                        // line 156
                        echo "                        <br>
                        ";
                        // line 157
                        echo twig_escape_filter($this->env, sprintf(_gettext("Polygon %d:"), ($context["k"] + 1)), "html", null, true);
                        echo "
                        ";
                        // line 158
                        $context["no_of_lines"] = (((twig_get_attribute($this->env, $this->source, twig_get_attribute($this->env, $this->source, twig_get_attribute($this->env, $this->source, twig_get_attribute($this->env, $this->source, ($context["gis_data"] ?? null), $context["a"], [], "array", false, true, false, 158), ($context["type"] ?? null), [], "array", false, true, false, 158), $context["k"], [], "array", false, true, false, 158), "no_of_lines", [], "array", true, true, false, 158) &&  !(null === (($__internal_51b47659448148079c55eb5fc84ce5e7b27c8ff1fadeba243d0bf4a59f102eb4 = twig_get_attribute($this->env, $this->source, twig_get_attribute($this->env, $this->source, twig_get_attribute($this->env, $this->source, ($context["gis_data"] ?? null), $context["a"], [], "array", false, true, false, 158), ($context["type"] ?? null), [], "array", false, true, false, 158), $context["k"], [], "array", false, true, false, 158)) && is_array($__internal_51b47659448148079c55eb5fc84ce5e7b27c8ff1fadeba243d0bf4a59f102eb4) || $__internal_51b47659448148079c55eb5fc84ce5e7b27c8ff1fadeba243d0bf4a59f102eb4 instanceof ArrayAccess ? ($__internal_51b47659448148079c55eb5fc84ce5e7b27c8ff1fadeba243d0bf4a59f102eb4["no_of_lines"] ?? null) : null)))) ? ((($__internal_7954abe9e82b868b32e99deec50bc82d0cf006d569340d1981c528f484e4306d = twig_get_attribute($this->env, $this->source, twig_get_attribute($this->env, $this->source, twig_get_attribute($this->env, $this->source, ($context["gis_data"] ?? null), $context["a"], [], "array", false, true, false, 158), ($context["type"] ?? null), [], "array", false, true, false, 158), $context["k"], [], "array", false, true, false, 158)) && is_array($__internal_7954abe9e82b868b32e99deec50bc82d0cf006d569340d1981c528f484e4306d) || $__internal_7954abe9e82b868b32e99deec50bc82d0cf006d569340d1981c528f484e4306d instanceof ArrayAccess ? ($__internal_7954abe9e82b868b32e99deec50bc82d0cf006d569340d1981c528f484e4306d["no_of_lines"] ?? null) : null)) : (1));
                        // line 159
                        echo "                        ";
                        if ((($context["no_of_lines"] ?? null) < 1)) {
                            // line 160
                            echo "                            ";
                            $context["no_of_lines"] = 1;
                            // line 161
                            echo "                        ";
                        }
                        // line 162
                        echo "                        ";
                        if ( !(null === (($__internal_edc3933374aa0ae65dd90505a315fe17c24a986a5b064b0f4774e7dc68df29b5 = (($__internal_78a78e2af552daad30f9bd5ea90c17811faa9f63aaaf1d1d527de70902fe2a7a = (($__internal_68329f830f66b3d66aa25264abe6d152d460842b92be66836c0d8febb9fe46da = (($__internal_0c0a6bc8299d1416ae3339265b194ff43aaec7fc209979ab91c947804ef09b38 = ($context["gis_data"] ?? null)) && is_array($__internal_0c0a6bc8299d1416ae3339265b194ff43aaec7fc209979ab91c947804ef09b38) || $__internal_0c0a6bc8299d1416ae3339265b194ff43aaec7fc209979ab91c947804ef09b38 instanceof ArrayAccess ? ($__internal_0c0a6bc8299d1416ae3339265b194ff43aaec7fc209979ab91c947804ef09b38[$context["a"]] ?? null) : null)) && is_array($__internal_68329f830f66b3d66aa25264abe6d152d460842b92be66836c0d8febb9fe46da) || $__internal_68329f830f66b3d66aa25264abe6d152d460842b92be66836c0d8febb9fe46da instanceof ArrayAccess ? ($__internal_68329f830f66b3d66aa25264abe6d152d460842b92be66836c0d8febb9fe46da[($context["type"] ?? null)] ?? null) : null)) && is_array($__internal_78a78e2af552daad30f9bd5ea90c17811faa9f63aaaf1d1d527de70902fe2a7a) || $__internal_78a78e2af552daad30f9bd5ea90c17811faa9f63aaaf1d1d527de70902fe2a7a instanceof ArrayAccess ? ($__internal_78a78e2af552daad30f9bd5ea90c17811faa9f63aaaf1d1d527de70902fe2a7a[$context["k"]] ?? null) : null)) && is_array($__internal_edc3933374aa0ae65dd90505a315fe17c24a986a5b064b0f4774e7dc68df29b5) || $__internal_edc3933374aa0ae65dd90505a315fe17c24a986a5b064b0f4774e7dc68df29b5 instanceof ArrayAccess ? ($__internal_edc3933374aa0ae65dd90505a315fe17c24a986a5b064b0f4774e7dc68df29b5["add_line"] ?? null) : null))) {
                            // line 163
                            echo "                            ";
                            $context["no_of_lines"] = (($context["no_of_lines"] ?? null) + 1);
                            // line 164
                            echo "                        ";
                        }
                        // line 165
                        echo "                        <input type=\"hidden\" name=\"gis_data[";
                        echo twig_escape_filter($this->env, $context["a"], "html", null, true);
                        echo "][";
                        echo twig_escape_filter($this->env, ($context["type"] ?? null), "html", null, true);
                        echo "][";
                        echo twig_escape_filter($this->env, $context["k"], "html", null, true);
                        echo "][no_of_lines]\" value=\"";
                        echo twig_escape_filter($this->env, ($context["no_of_lines"] ?? null), "html", null, true);
                        echo "\">

                        ";
                        // line 167
                        $context['_parent'] = $context;
                        $context['_seq'] = twig_ensure_traversable(range(0, (($context["no_of_lines"] ?? null) - 1)));
                        foreach ($context['_seq'] as $context["_key"] => $context["i"]) {
                            // line 168
                            echo "                            <br><br>
                            ";
                            // line 169
                            if (($context["i"] == 0)) {
                                // line 170
                                echo "                                ";
                                echo _gettext("Outer ring:");
                                // line 171
                                echo "                            ";
                            } else {
                                // line 172
                                echo "                                ";
                                echo twig_escape_filter($this->env, sprintf(_gettext("Inner ring %d:"), $context["i"]), "html", null, true);
                                echo "
                            ";
                            }
                            // line 174
                            echo "
                            ";
                            // line 175
                            $context["no_of_points"] = (((twig_get_attribute($this->env, $this->source, twig_get_attribute($this->env, $this->source, twig_get_attribute($this->env, $this->source, twig_get_attribute($this->env, $this->source, twig_get_attribute($this->env, $this->source, ($context["gis_data"] ?? null), $context["a"], [], "array", false, true, false, 175), ($context["type"] ?? null), [], "array", false, true, false, 175), $context["k"], [], "array", false, true, false, 175), $context["i"], [], "array", false, true, false, 175), "no_of_points", [], "array", true, true, false, 175) &&  !(null === (($__internal_c5373d6c112ec7cfa0d260a8ea49b75af689c74c186cb9e1d12f91be2f3451ec = twig_get_attribute($this->env, $this->source, twig_get_attribute($this->env, $this->source, twig_get_attribute($this->env, $this->source, twig_get_attribute($this->env, $this->source, ($context["gis_data"] ?? null), $context["a"], [], "array", false, true, false, 175), ($context["type"] ?? null), [], "array", false, true, false, 175), $context["k"], [], "array", false, true, false, 175), $context["i"], [], "array", false, true, false, 175)) && is_array($__internal_c5373d6c112ec7cfa0d260a8ea49b75af689c74c186cb9e1d12f91be2f3451ec) || $__internal_c5373d6c112ec7cfa0d260a8ea49b75af689c74c186cb9e1d12f91be2f3451ec instanceof ArrayAccess ? ($__internal_c5373d6c112ec7cfa0d260a8ea49b75af689c74c186cb9e1d12f91be2f3451ec["no_of_points"] ?? null) : null)))) ? ((($__internal_a13b5858c5824edc0cf555cffe22c4f90468c22ef1115c74916647af2c9b8574 = twig_get_attribute($this->env, $this->source, twig_get_attribute($this->env, $this->source, twig_get_attribute($this->env, $this->source, twig_get_attribute($this->env, $this->source, ($context["gis_data"] ?? null), $context["a"], [], "array", false, true, false, 175), ($context["type"] ?? null), [], "array", false, true, false, 175), $context["k"], [], "array", false, true, false, 175), $context["i"], [], "array", false, true, false, 175)) && is_array($__internal_a13b5858c5824edc0cf555cffe22c4f90468c22ef1115c74916647af2c9b8574) || $__internal_a13b5858c5824edc0cf555cffe22c4f90468c22ef1115c74916647af2c9b8574 instanceof ArrayAccess ? ($__internal_a13b5858c5824edc0cf555cffe22c4f90468c22ef1115c74916647af2c9b8574["no_of_points"] ?? null) : null)) : (4));
                            // line 176
                            echo "                            ";
                            if ((($context["no_of_points"] ?? null) < 4)) {
                                // line 177
                                echo "                                ";
                                $context["no_of_points"] = 4;
                                // line 178
                                echo "                            ";
                            }
                            // line 179
                            echo "                            ";
                            if ( !(null === (($__internal_8273200462706e912633c1bd12ca5fc5736d038390c29954112cb78d56c3075c = (($__internal_ba7685baed7d294d6f9f021c094359707afc43c727e6a2d19ff1d176796bbda0 = (($__internal_101f955954d09941874d68c1bc31b2171b1313930c7c7163a30d4c0951b92adc = (($__internal_d19b8970b34a70cf90f25bc70d063a8b0fc361c2ffc373a6176195b465bc0ccd = (($__internal_7f22f462d0a079e9b28d4dd0209cce239cda0d0c81b8f79d4d6355c3a5eedc81 = ($context["gis_data"] ?? null)) && is_array($__internal_7f22f462d0a079e9b28d4dd0209cce239cda0d0c81b8f79d4d6355c3a5eedc81) || $__internal_7f22f462d0a079e9b28d4dd0209cce239cda0d0c81b8f79d4d6355c3a5eedc81 instanceof ArrayAccess ? ($__internal_7f22f462d0a079e9b28d4dd0209cce239cda0d0c81b8f79d4d6355c3a5eedc81[$context["a"]] ?? null) : null)) && is_array($__internal_d19b8970b34a70cf90f25bc70d063a8b0fc361c2ffc373a6176195b465bc0ccd) || $__internal_d19b8970b34a70cf90f25bc70d063a8b0fc361c2ffc373a6176195b465bc0ccd instanceof ArrayAccess ? ($__internal_d19b8970b34a70cf90f25bc70d063a8b0fc361c2ffc373a6176195b465bc0ccd[($context["type"] ?? null)] ?? null) : null)) && is_array($__internal_101f955954d09941874d68c1bc31b2171b1313930c7c7163a30d4c0951b92adc) || $__internal_101f955954d09941874d68c1bc31b2171b1313930c7c7163a30d4c0951b92adc instanceof ArrayAccess ? ($__internal_101f955954d09941874d68c1bc31b2171b1313930c7c7163a30d4c0951b92adc[$context["k"]] ?? null) : null)) && is_array($__internal_ba7685baed7d294d6f9f021c094359707afc43c727e6a2d19ff1d176796bbda0) || $__internal_ba7685baed7d294d6f9f021c094359707afc43c727e6a2d19ff1d176796bbda0 instanceof ArrayAccess ? ($__internal_ba7685baed7d294d6f9f021c094359707afc43c727e6a2d19ff1d176796bbda0[$context["i"]] ?? null) : null)) && is_array($__internal_8273200462706e912633c1bd12ca5fc5736d038390c29954112cb78d56c3075c) || $__internal_8273200462706e912633c1bd12ca5fc5736d038390c29954112cb78d56c3075c instanceof ArrayAccess ? ($__internal_8273200462706e912633c1bd12ca5fc5736d038390c29954112cb78d56c3075c["add_point"] ?? null) : null))) {
                                // line 180
                                echo "                                ";
                                $context["no_of_points"] = (($context["no_of_points"] ?? null) + 1);
                                // line 181
                                echo "                            ";
                            }
                            // line 182
                            echo "                            <input type=\"hidden\" name=\"gis_data[";
                            echo twig_escape_filter($this->env, $context["a"], "html", null, true);
                            echo "][";
                            echo twig_escape_filter($this->env, ($context["type"] ?? null), "html", null, true);
                            echo "][";
                            echo twig_escape_filter($this->env, $context["k"], "html", null, true);
                            echo "][";
                            echo twig_escape_filter($this->env, $context["i"], "html", null, true);
                            echo "][no_of_points]\" value=\"";
                            echo twig_escape_filter($this->env, ($context["no_of_points"] ?? null), "html", null, true);
                            echo "\">

                            ";
                            // line 184
                            $context['_parent'] = $context;
                            $context['_seq'] = twig_ensure_traversable(range(0, (($context["no_of_points"] ?? null) - 1)));
                            foreach ($context['_seq'] as $context["_key"] => $context["j"]) {
                                // line 185
                                echo "                                <br>
                                ";
                                // line 186
                                echo twig_escape_filter($this->env, sprintf(_gettext("Point %d:"), ($context["j"] + 1)), "html", null, true);
                                echo "
                                <label for=\"x\">";
                                // line 187
                                echo _gettext("X");
                                echo "</label>
                                <input type=\"text\" name=\"gis_data[";
                                // line 188
                                echo twig_escape_filter($this->env, $context["a"], "html", null, true);
                                echo "][";
                                echo twig_escape_filter($this->env, ($context["type"] ?? null), "html", null, true);
                                echo "][";
                                echo twig_escape_filter($this->env, $context["k"], "html", null, true);
                                echo "][";
                                echo twig_escape_filter($this->env, $context["i"], "html", null, true);
                                echo "][";
                                echo twig_escape_filter($this->env, $context["j"], "html", null, true);
                                echo "][x]\" value=\"";
                                echo twig_escape_filter($this->env, (($__internal_08d357d6c6cc179c7eaa6aa16ae7c13336efbc0aa5669c58d46cab7f2ce96007 = (($__internal_6d2de8847ca935d43c4b17225dc2537ff47d9b1c0e614e4fed558aa26b7f934d = (($__internal_14ec589d07a85756e12acaaf8d41cc27621a5a03ce9e1c2835143b81f89a8dba = (($__internal_15cadc33e29273b0be5cf970bdbb25fb0d962f226cb329dfeb89075c4a503f49 = (($__internal_fdffc6d7d2105180aa5315b58ff859ceee4ece5e5b7b2601a851c7a60a10d639 = (($__internal_d3425701b9a0a8a13b32495933a7425cc5de4c0e5eb576b5e6202e761600efaf = ($context["gis_data"] ?? null)) && is_array($__internal_d3425701b9a0a8a13b32495933a7425cc5de4c0e5eb576b5e6202e761600efaf) || $__internal_d3425701b9a0a8a13b32495933a7425cc5de4c0e5eb576b5e6202e761600efaf instanceof ArrayAccess ? ($__internal_d3425701b9a0a8a13b32495933a7425cc5de4c0e5eb576b5e6202e761600efaf[$context["a"]] ?? null) : null)) && is_array($__internal_fdffc6d7d2105180aa5315b58ff859ceee4ece5e5b7b2601a851c7a60a10d639) || $__internal_fdffc6d7d2105180aa5315b58ff859ceee4ece5e5b7b2601a851c7a60a10d639 instanceof ArrayAccess ? ($__internal_fdffc6d7d2105180aa5315b58ff859ceee4ece5e5b7b2601a851c7a60a10d639[($context["type"] ?? null)] ?? null) : null)) && is_array($__internal_15cadc33e29273b0be5cf970bdbb25fb0d962f226cb329dfeb89075c4a503f49) || $__internal_15cadc33e29273b0be5cf970bdbb25fb0d962f226cb329dfeb89075c4a503f49 instanceof ArrayAccess ? ($__internal_15cadc33e29273b0be5cf970bdbb25fb0d962f226cb329dfeb89075c4a503f49[$context["k"]] ?? null) : null)) && is_array($__internal_14ec589d07a85756e12acaaf8d41cc27621a5a03ce9e1c2835143b81f89a8dba) || $__internal_14ec589d07a85756e12acaaf8d41cc27621a5a03ce9e1c2835143b81f89a8dba instanceof ArrayAccess ? ($__internal_14ec589d07a85756e12acaaf8d41cc27621a5a03ce9e1c2835143b81f89a8dba[$context["i"]] ?? null) : null)) && is_array($__internal_6d2de8847ca935d43c4b17225dc2537ff47d9b1c0e614e4fed558aa26b7f934d) || $__internal_6d2de8847ca935d43c4b17225dc2537ff47d9b1c0e614e4fed558aa26b7f934d instanceof ArrayAccess ? ($__internal_6d2de8847ca935d43c4b17225dc2537ff47d9b1c0e614e4fed558aa26b7f934d[$context["j"]] ?? null) : null)) && is_array($__internal_08d357d6c6cc179c7eaa6aa16ae7c13336efbc0aa5669c58d46cab7f2ce96007) || $__internal_08d357d6c6cc179c7eaa6aa16ae7c13336efbc0aa5669c58d46cab7f2ce96007 instanceof ArrayAccess ? ($__internal_08d357d6c6cc179c7eaa6aa16ae7c13336efbc0aa5669c58d46cab7f2ce96007["x"] ?? null) : null), "html", null, true);
                                echo "\">
                                <label for=\"y\">";
                                // line 189
                                echo _gettext("Y");
                                echo "</label>
                                <input type=\"text\" name=\"gis_data[";
                                // line 190
                                echo twig_escape_filter($this->env, $context["a"], "html", null, true);
                                echo "][";
                                echo twig_escape_filter($this->env, ($context["type"] ?? null), "html", null, true);
                                echo "][";
                                echo twig_escape_filter($this->env, $context["k"], "html", null, true);
                                echo "][";
                                echo twig_escape_filter($this->env, $context["i"], "html", null, true);
                                echo "][";
                                echo twig_escape_filter($this->env, $context["j"], "html", null, true);
                                echo "][y]\" value=\"";
                                echo twig_escape_filter($this->env, (($__internal_aee130853742ef3e066bee9d5b201f026709112632574a72409cce5c24f44921 = (($__internal_34bfc9d3bb99a6e1ea80e9e1e16e70ac03c16465a14de0faf0a7d7df04205a7a = (($__internal_975fa751a8f688c78873ea77782d85542baaefa8277fb1ae2e9b2a7d8eed4ca4 = (($__internal_3a29dd8c635325e3d124a8a60682c8c1d72c8f81204e952bf98350c9fabbc985 = (($__internal_245fa8e4b1f2520e359eeb249916824c4d6f6fcce189eedb4956fb1747c4eb51 = (($__internal_9e80f0131faf7c30fa2ce2a767187df174f9da8bcbd50f87a692ce0bfaa1635a = ($context["gis_data"] ?? null)) && is_array($__internal_9e80f0131faf7c30fa2ce2a767187df174f9da8bcbd50f87a692ce0bfaa1635a) || $__internal_9e80f0131faf7c30fa2ce2a767187df174f9da8bcbd50f87a692ce0bfaa1635a instanceof ArrayAccess ? ($__internal_9e80f0131faf7c30fa2ce2a767187df174f9da8bcbd50f87a692ce0bfaa1635a[$context["a"]] ?? null) : null)) && is_array($__internal_245fa8e4b1f2520e359eeb249916824c4d6f6fcce189eedb4956fb1747c4eb51) || $__internal_245fa8e4b1f2520e359eeb249916824c4d6f6fcce189eedb4956fb1747c4eb51 instanceof ArrayAccess ? ($__internal_245fa8e4b1f2520e359eeb249916824c4d6f6fcce189eedb4956fb1747c4eb51[($context["type"] ?? null)] ?? null) : null)) && is_array($__internal_3a29dd8c635325e3d124a8a60682c8c1d72c8f81204e952bf98350c9fabbc985) || $__internal_3a29dd8c635325e3d124a8a60682c8c1d72c8f81204e952bf98350c9fabbc985 instanceof ArrayAccess ? ($__internal_3a29dd8c635325e3d124a8a60682c8c1d72c8f81204e952bf98350c9fabbc985[$context["k"]] ?? null) : null)) && is_array($__internal_975fa751a8f688c78873ea77782d85542baaefa8277fb1ae2e9b2a7d8eed4ca4) || $__internal_975fa751a8f688c78873ea77782d85542baaefa8277fb1ae2e9b2a7d8eed4ca4 instanceof ArrayAccess ? ($__internal_975fa751a8f688c78873ea77782d85542baaefa8277fb1ae2e9b2a7d8eed4ca4[$context["i"]] ?? null) : null)) && is_array($__internal_34bfc9d3bb99a6e1ea80e9e1e16e70ac03c16465a14de0faf0a7d7df04205a7a) || $__internal_34bfc9d3bb99a6e1ea80e9e1e16e70ac03c16465a14de0faf0a7d7df04205a7a instanceof ArrayAccess ? ($__internal_34bfc9d3bb99a6e1ea80e9e1e16e70ac03c16465a14de0faf0a7d7df04205a7a[$context["j"]] ?? null) : null)) && is_array($__internal_aee130853742ef3e066bee9d5b201f026709112632574a72409cce5c24f44921) || $__internal_aee130853742ef3e066bee9d5b201f026709112632574a72409cce5c24f44921 instanceof ArrayAccess ? ($__internal_aee130853742ef3e066bee9d5b201f026709112632574a72409cce5c24f44921["y"] ?? null) : null), "html", null, true);
                                echo "\">
                            ";
                            }
                            $_parent = $context['_parent'];
                            unset($context['_seq'], $context['_iterated'], $context['_key'], $context['j'], $context['_parent'], $context['loop']);
                            $context = array_intersect_key($context, $_parent) + $_parent;
                            // line 192
                            echo "                            <input type=\"submit\" name=\"gis_data[";
                            echo twig_escape_filter($this->env, $context["a"], "html", null, true);
                            echo "][";
                            echo twig_escape_filter($this->env, ($context["type"] ?? null), "html", null, true);
                            echo "][";
                            echo twig_escape_filter($this->env, $context["k"], "html", null, true);
                            echo "][";
                            echo twig_escape_filter($this->env, $context["i"], "html", null, true);
                            echo "][add_point]\" class=\"btn btn-secondary add addPoint\" value=\"";
                            echo _gettext("Add a point");
                            echo "\">
                        ";
                        }
                        $_parent = $context['_parent'];
                        unset($context['_seq'], $context['_iterated'], $context['_key'], $context['i'], $context['_parent'], $context['loop']);
                        $context = array_intersect_key($context, $_parent) + $_parent;
                        // line 194
                        echo "                        <br>
                        <input type=\"submit\" name=\"gis_data[";
                        // line 195
                        echo twig_escape_filter($this->env, $context["a"], "html", null, true);
                        echo "][";
                        echo twig_escape_filter($this->env, ($context["type"] ?? null), "html", null, true);
                        echo "][";
                        echo twig_escape_filter($this->env, $context["k"], "html", null, true);
                        echo "][add_line]\" class=\"btn btn-secondary add addLine\" value=\"";
                        echo _gettext("Add an inner ring");
                        echo "\">
                        <br>
                    ";
                    }
                    $_parent = $context['_parent'];
                    unset($context['_seq'], $context['_iterated'], $context['_key'], $context['k'], $context['_parent'], $context['loop']);
                    $context = array_intersect_key($context, $_parent) + $_parent;
                    // line 198
                    echo "                    <br>
                    <input type=\"submit\" name=\"gis_data[";
                    // line 199
                    echo twig_escape_filter($this->env, $context["a"], "html", null, true);
                    echo "][";
                    echo twig_escape_filter($this->env, ($context["type"] ?? null), "html", null, true);
                    echo "][add_polygon]\" class=\"btn btn-secondary add addPolygon\" value=\"";
                    echo _gettext("Add a polygon");
                    echo "\">
                ";
                }
                // line 201
                echo "              ";
            }
            // line 202
            echo "            ";
        }
        $_parent = $context['_parent'];
        unset($context['_seq'], $context['_iterated'], $context['_key'], $context['a'], $context['_parent'], $context['loop']);
        $context = array_intersect_key($context, $_parent) + $_parent;
        // line 203
        echo "            ";
        if ((($context["geom_type"] ?? null) == "GEOMETRYCOLLECTION")) {
            // line 204
            echo "                <br><br>
                <input type=\"submit\" name=\"gis_data[GEOMETRYCOLLECTION][add_geom]\" class=\"btn btn-secondary add addGeom\" value=\"";
            // line 205
            echo _gettext("Add geometry");
            echo "\">
            ";
        }
        // line 207
        echo "        </div>
        ";
        // line 209
        echo "
        <br>
        <input class=\"btn btn-primary\" type=\"submit\" name=\"gis_data[save]\" value=\"";
        // line 211
        echo _gettext("Go");
        echo "\">

        <div id=\"gis_data_output\">
            <h3>";
        // line 214
        echo _gettext("Output");
        echo "</h3>
            <p>
                ";
        // line 216
        echo _gettext("Choose \"GeomFromText\" from the \"Function\" column and paste the string below into the \"Value\" field.");
        // line 217
        echo "            </p>
            <textarea id=\"gis_data_textarea\" cols=\"95\" rows=\"5\">";
        // line 218
        echo twig_escape_filter($this->env, ($context["result"] ?? null), "html", null, true);
        echo "</textarea>
        </div>
    </div>
</form>
";
    }

    public function getTemplateName()
    {
        return "gis_data_editor_form.twig";
    }

    public function isTraitable()
    {
        return false;
    }

    public function getDebugInfo()
    {
        return array (  809 => 218,  806 => 217,  804 => 216,  799 => 214,  793 => 211,  789 => 209,  786 => 207,  781 => 205,  778 => 204,  775 => 203,  769 => 202,  766 => 201,  757 => 199,  754 => 198,  739 => 195,  736 => 194,  719 => 192,  701 => 190,  697 => 189,  683 => 188,  679 => 187,  675 => 186,  672 => 185,  668 => 184,  654 => 182,  651 => 181,  648 => 180,  645 => 179,  642 => 178,  639 => 177,  636 => 176,  634 => 175,  631 => 174,  625 => 172,  622 => 171,  619 => 170,  617 => 169,  614 => 168,  610 => 167,  598 => 165,  595 => 164,  592 => 163,  589 => 162,  586 => 161,  583 => 160,  580 => 159,  578 => 158,  574 => 157,  571 => 156,  567 => 155,  557 => 153,  554 => 152,  551 => 151,  548 => 150,  545 => 149,  542 => 148,  539 => 147,  536 => 146,  534 => 145,  530 => 144,  525 => 143,  522 => 142,  507 => 140,  491 => 138,  487 => 137,  475 => 136,  471 => 135,  467 => 134,  464 => 133,  460 => 132,  448 => 130,  445 => 129,  442 => 128,  439 => 127,  436 => 126,  433 => 125,  430 => 124,  427 => 123,  424 => 122,  421 => 121,  419 => 120,  416 => 119,  410 => 117,  407 => 116,  404 => 115,  402 => 114,  397 => 113,  395 => 112,  392 => 111,  388 => 110,  378 => 108,  375 => 107,  372 => 106,  369 => 105,  366 => 104,  363 => 103,  360 => 102,  357 => 101,  355 => 100,  346 => 99,  332 => 97,  328 => 96,  318 => 95,  314 => 94,  310 => 93,  307 => 92,  303 => 91,  293 => 89,  290 => 88,  287 => 87,  284 => 86,  281 => 85,  278 => 84,  275 => 83,  272 => 82,  269 => 81,  266 => 80,  263 => 79,  261 => 78,  255 => 77,  251 => 76,  245 => 75,  240 => 74,  238 => 73,  235 => 72,  233 => 71,  230 => 70,  227 => 69,  224 => 68,  220 => 66,  211 => 63,  204 => 62,  200 => 61,  195 => 60,  192 => 59,  189 => 58,  186 => 57,  183 => 56,  181 => 55,  176 => 53,  173 => 52,  170 => 51,  167 => 50,  163 => 49,  160 => 48,  154 => 46,  152 => 45,  149 => 44,  146 => 42,  141 => 39,  137 => 38,  133 => 36,  124 => 33,  117 => 32,  113 => 31,  109 => 29,  106 => 27,  102 => 25,  96 => 22,  92 => 21,  82 => 18,  76 => 15,  71 => 14,  65 => 11,  59 => 9,  56 => 8,  52 => 6,  47 => 4,  42 => 2,  37 => 1,);
    }

    public function getSourceContext()
    {
        return new Source("", "gis_data_editor_form.twig", "C:\\MAMP\\htdocs\\phpmyadmin\\templates\\gis_data_editor_form.twig");
    }
}
